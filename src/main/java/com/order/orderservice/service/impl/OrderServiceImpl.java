package com.order.orderservice.service.impl;

import com.order.orderservice.dto.OrderRequest;
import com.order.orderservice.dto.OrderResponse;
import com.order.orderservice.entity.Order;
import com.order.orderservice.enums.OrderStatus;
import com.order.orderservice.enums.PaymentStatus;
import com.order.orderservice.exception.OrderNotFoundException;
import com.order.orderservice.repository.OrderRepository;
import com.order.orderservice.service.OrderService;
import org.springframework.data.domain.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;

import org.springframework.data.domain.PageRequest;
import com.order.orderservice.dto.PageResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;


import java.util.List;



@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    // -------------------- Create Order --------------------

    @Override
    public OrderResponse createOrder(OrderRequest request) {

        log.info("Received request to create order : {}", request.getOrderNumber());

        // -------------------- Step 1 : Create Order Entity --------------------

        Order order = Order.builder()
                .orderNumber(request.getOrderNumber())
                .customerId(request.getCustomerId())
                .productId(request.getProductId())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .totalAmount(request.getTotalAmount())
                .orderStatus(request.getOrderStatus())
                .paymentStatus(request.getPaymentStatus())
                .orderDate(request.getOrderDate())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        // -------------------- Step 2 : Save Entity into Database --------------------

        Order savedOrder = orderRepository.save(order);

        log.info("Order created successfully with ID : {}", savedOrder.getId());

        // -------------------- Step 3 : Convert Entity to Response DTO --------------------

        return mapToResponse(savedOrder);

    }




    // -------------------- Get All Orders --------------------



    @Override
    public Page<OrderResponse> getAllOrders(int page, int size,String sortBy,String direction) {

        // ==========================================================
        // Log the incoming pagination and sorting request
        // ==========================================================
        log.info("Received request to fetch all orders | Page : {} | Size : {} | Sort By : Price | Direction : ASC",
                page, size);

        // ==========================================================
        // Create Sort Object
        // Sort records by Price in Ascending Order
        // ASC = Small to Large
        // ==========================================================
        Sort sort = Sort.by(Sort.Direction.ASC, "price");

        // ==========================================================
        // Create Pageable Object
        // Pagination + Sorting
        // page -> Current page number
        // size -> Number of records per page
        // sort -> Sort by Price (Ascending)
        // ==========================================================
        Pageable pageable = PageRequest.of(page, size, sort);

        // ==========================================================
        // Fetch only the required page from database
        // along with sorting
        // ==========================================================
        Page<Order> orderPage = orderRepository.findAll(pageable);

        // ==========================================================
        // Convert Page<Order> into Page<OrderResponse>
        // map() automatically converts each Order object
        // into OrderResponse
        // ==========================================================
        Page<OrderResponse> responsePage = orderPage.map(this::mapToResponse);

        // ==========================================================
        // Log successful execution
        // ==========================================================
        log.info("Successfully fetched page {} with {} records sorted by Price in Ascending Order",
                page,
                responsePage.getNumberOfElements());

        // ==========================================================
        // Return paginated and sorted response
        // ==========================================================
        return responsePage;
    }
   /* @Override
    public List<OrderResponse> getAllOrders() {

        log.info("Received request to fetch all orders");

        // -------------------- Step 1 : Fetch All Orders from Database --------------------

        List<Order> orders = orderRepository.findAll();

        log.info("Fetched {} orders from database", orders.size());

        // -------------------- Step 2 : Create Response DTO List --------------------

        List<OrderResponse> responses = new ArrayList<>();

        // -------------------- Step 3 : Convert Entity List to Response DTO List --------------------

        for (Order order : orders) {

            responses.add(mapToResponse(order));

        }

        // -------------------- Step 4 : Return Response DTO List --------------------

        log.info("Returning {} orders", responses.size());

        return responses;}*/





    // -------------------- Get Order By Id --------------------

    @Override
    public OrderResponse getOrderById(Long id) {

        log.info("Received request to fetch order with Id: {}",id);

        // -------------------- Step 1 : Fetch Order from Database --------------------

        Order order = orderRepository.findById(id).orElseThrow(()->{

            log.error("Order not found with Id : {}",id);

            return new OrderNotFoundException("Order not found with Id :"+id);

        });
        log.info("Order found with Id : {}",id);

        // -------------------- Step 2 : Convert Entity to Response DTO --------------------

        OrderResponse response = mapToResponse(order);

        // -------------------- Step 3 : Return Response DTO --------------------

        log.info("Returning Order Response for ID : {}", id);

        return response;
    }

    // -------------------- Update Order --------------------

    @Override
    public OrderResponse updateOrder(Long id, OrderRequest request) {

        log.info("Updating order with ID : {}",id);

        // Step 1 : Find existing order

        Order order = orderRepository.findById(id).orElseThrow(()->{log.error("Order not found with ID : {}",id);
        return new OrderNotFoundException("Order not Found with ID :"+ id);
        });

        // Step 2 : Update order fields
        order.setOrderNumber(request.getOrderNumber());
        order.setCustomerId(request.getCustomerId());
        order.setProductId(request.getProductId());
        order.setQuantity(request.getQuantity());
        order.setPrice(request.getPrice());

        // Step 3 : Recalculate total amount
        order.setTotalAmount(request.getQuantity() * request.getPrice());

        // Step 4 : Save updated order
        Order updatedOrder = orderRepository.save(order);

        log.info("Order updated successfully with ID : {}", updatedOrder.getId());

        // Step 5 : Convert Entity to Response DTO
        return mapToResponse(updatedOrder);


    }

    // -------------------- Partial Update Order --------------------

    @Override
    public OrderResponse partialUpdateOrder(Long id, OrderRequest request) {

        log.info("Partially updating order with ID : {}", id);

        // Step 1 : Find existing order
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Order not found with ID : {}", id);
                    return new OrderNotFoundException("Order not found with ID : " + id);
                });

        // Step 2 : Update only provided fields

        if (request.getOrderNumber() != null) {
            order.setOrderNumber(request.getOrderNumber());
        }

        if (request.getCustomerId() != null) {
            order.setCustomerId(request.getCustomerId());
        }

        if (request.getProductId() != null) {
            order.setProductId(request.getProductId());
        }

        if (request.getQuantity() != null) {
            order.setQuantity(request.getQuantity());
        }

        if (request.getPrice() != null) {
            order.setPrice(request.getPrice());
        }

        // Step 3 : Recalculate total amount
        order.setTotalAmount(order.getQuantity() * order.getPrice());

        // Step 4 : Save updated order
        Order updatedOrder = orderRepository.save(order);

        log.info("Order partially updated successfully with ID : {}", updatedOrder.getId());

        // Step 5 : Convert Entity to Response DTO
        return mapToResponse(updatedOrder);
    }

    // -------------------- Delete Order --------------------

    @Override
    public void deleteOrder(Long id) {

        log.info("Deleting orderwith ID : {}",id);

        // Step 1 : Find existing order
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Order not found with ID : {}", id);
                    return new OrderNotFoundException("Order not found with ID : " + id);
                });

        // Step 2 : Delete order from database
        orderRepository.delete(order);

        // Step 3 : Log successful deletion
        log.info("Order deleted successfully with ID : {}", id);


    }


    // ========================== CURD APIS COMPLETED HERE  ==========================


    // ==========================
   // Get Order By Order Number
  // ==========================

    @Override
    public OrderResponse getOrderByOrderNumber(String orderNumber) {

        log.info("Received request to fetch order with order number : {}", orderNumber);

        // -------------------- Step 1 : Fetch Order from Database --------------------

        Order order = orderRepository.findByOrderNumber(orderNumber).orElseThrow(() -> {

                    log.error("Order not found with order number : {}", orderNumber);

                    return new OrderNotFoundException("Order not found with order number : " + orderNumber);

                });

        log.info("Order found with order number : {}", orderNumber);

        // -------------------- Step 2 : Convert Entity to Response DTO --------------------

        OrderResponse response = mapToResponse(order);

        // -------------------- Step 3 : Return Response DTO --------------------

        log.info("Returning Order Response for Order Number : {}", orderNumber);

        return response;
    }



    // ==========================================================
// Get Customer Orders With Pagination + Sorting
// ==========================================================
    @Override
    public PageResponse<OrderResponse> getOrdersByCustomerId(
            Long customerId,
            int page,
            int size,
            String sortBy,
            String direction) {

        // ==========================================================
        // Log Incoming Request
        // ==========================================================
        log.info(
                "Fetching customer orders. Customer ID : {}, Page : {}, Size : {}, Sort By : {}, Direction : {}",
                customerId, page, size, sortBy, direction);

        // ==========================================================
        // Convert ASC / DESC Into Sort Direction
        // ==========================================================
        Sort.Direction sortDirection = Sort.Direction.fromString(direction);

        // ==========================================================
        // Create Sort Object
        // ==========================================================
        Sort sort = Sort.by(sortDirection, sortBy);

        // ==========================================================
        // Create Pageable Object
        // ==========================================================
        Pageable pageable = PageRequest.of(page, size, sort);

        // ==========================================================
        // Fetch Customer Orders
        // ==========================================================
        Page<Order> orderPage = orderRepository.findByCustomerId(customerId, pageable);

        // ==========================================================
        // Throw Exception If No Orders Found
        // ==========================================================
        if (orderPage.isEmpty()) {

            log.warn("No orders found for customer ID : {}", customerId);

            throw new OrderNotFoundException(
                    "No orders found for customer ID : " + customerId);
        }

        // ==========================================================
        // Log Successful Fetch
        // ==========================================================
        log.info(
                "Successfully fetched {} orders for customer ID : {}",
                orderPage.getNumberOfElements(),
                customerId);

        // ==========================================================
// Convert Entity List Into DTO List
// ==========================================================
        List<OrderResponse> orderResponses = orderPage.getContent()
                .stream()
                .map(this::mapToResponse)
                .toList();

// ==========================================================
// Prepare Pagination Response
// ==========================================================
        PageResponse<OrderResponse> response = new PageResponse<>();

// ==========================================================
// Set Response Data
// ==========================================================
        response.setContent(orderResponses);
        response.setPage(orderPage.getNumber());
        response.setSize(orderPage.getSize());
        response.setTotalElements(orderPage.getTotalElements());
        response.setTotalPages(orderPage.getTotalPages());
        response.setFirst(orderPage.isFirst());
        response.setLast(orderPage.isLast());

// ==========================================================
// Return Response
// ==========================================================
        return response;
    }

         /* // ==========================
         // Get Orders By Customer ID
    // ==========================

    @Override
    public List<OrderResponse> getOrdersByCustomerId(Long customerId) {

        log.info("Received request to fetch orders with customer id : {}", customerId);

        // -------------------- Step 1 : Fetch Orders from Database --------------------

        List<Order> orders = orderRepository.findByCustomerId(customerId);

        if (orders.isEmpty()) {

            log.error("No orders found with customer id : {}", customerId);

            throw new OrderNotFoundException(
                    "No orders found with customer id : " + customerId);

        }

        log.info("Successfully fetched {} orders with customer id : {}", orders.size(), customerId);

        // -------------------- Step 2 : Convert Entity List to Response DTO List --------------------

        List<OrderResponse> response = orders.stream().map(this::mapToResponse).toList();

        // -------------------- Step 3 : Return Response DTO List --------------------

        log.info("Returning {} orders for customer id : {}", response.size(), customerId);

        return response;
    }
*/


    // ==========================================================
// Get Product Orders With Pagination + Sorting
// ==========================================================
    @Override
    public PageResponse<OrderResponse> getOrdersByProductId(
            Long productId,
            int page,
            int size,
            String sortBy,
            String direction) {

        // ==========================================================
        // Log Incoming Request
        // ==========================================================
        log.info("Fetching orders for product ID : {}", productId);

        // ==========================================================
        // Convert ASC / DESC Into Sort Direction
        // ==========================================================
        Sort.Direction sortDirection = Sort.Direction.fromString(direction);

        // ==========================================================
        // Create Sort Object
        // ==========================================================
        Sort sort = Sort.by(sortDirection, sortBy);

        // ==========================================================
        // Create Pageable Object
        // ==========================================================
        Pageable pageable = PageRequest.of(page, size, sort);

        // ==========================================================
        // Fetch Orders By Product ID
        // ==========================================================
        Page<Order> orderPage = orderRepository.findByProductId(productId, pageable);

        // ==========================================================
        // Throw Exception If No Orders Found
        // ==========================================================
        if (orderPage.isEmpty()) {

            throw new OrderNotFoundException("No orders found for product ID : " + productId);
        }

        // ==========================================================
        // Convert Entity List Into DTO List
        // ==========================================================
        List<OrderResponse> orderResponses =
                orderPage.getContent()
                        .stream()
                        .map(this::mapToResponse)
                        .toList();

        // ==========================================================
        // Log Success
        // ==========================================================
        log.info("Successfully fetched {} orders for product ID : {}",
                orderResponses.size(), productId);

        // ==========================================================
        // Prepare Pagination Response
        // ==========================================================
        PageResponse<OrderResponse> response = new PageResponse<>();

        // ==========================================================
        // Set Response Data
        // ==========================================================
        response.setContent(orderResponses);
        response.setPage(orderPage.getNumber());
        response.setSize(orderPage.getSize());
        response.setTotalElements(orderPage.getTotalElements());
        response.setTotalPages(orderPage.getTotalPages());
        response.setFirst(orderPage.isFirst());
        response.setLast(orderPage.isLast());

        // ==========================================================
        // Return Response
        // ==========================================================
        return response;
    }



   /* // ==========================
// Get Orders By Product ID
// ==========================

    @Override
    public List<OrderResponse> getOrdersByProductId(Long productId) {

        log.info("Received request to fetch orders with product id : {}", productId);

        // -------------------- Step 1 : Fetch Orders from Database --------------------

        List<Order> orders = orderRepository.findByProductId(productId);

        if (orders.isEmpty()) {

            log.error("No orders found with product id : {}", productId);

            throw new OrderNotFoundException("No orders found with product id : " + productId);

        }

        log.info("Successfully fetched {} orders with product id : {}", orders.size(), productId);

        // -------------------- Step 2 : Convert Entity List to Response DTO List --------------------

        List<OrderResponse> response = orders.stream()
                .map(this::mapToResponse)
                .toList();

        // -------------------- Step 3 : Return Response DTO List --------------------

        log.info("Returning {} orders for product id : {}", response.size(), productId);

        return response;

    }

*/
// ==========================
// Get Orders By Order Status
// ==========================

    @Override
    public List<OrderResponse> getOrdersByOrderStatus(OrderStatus orderStatus) {

        log.info("Received request to fetch orders with status : {}", orderStatus);

        // -------------------- Step 1 : Fetch Orders from Database --------------------

        List<Order> orders = orderRepository.findByOrderStatus(orderStatus);

        if (orders.isEmpty()) {

            log.error("No orders found with status : {}", orderStatus);

            throw new OrderNotFoundException("No orders found with status : " + orderStatus);
        }

        // -------------------- Step 2 : Convert Entity List into Response List --------------------

        List<OrderResponse> responseList = orders.stream()
                .map(this::mapToResponse)
                .toList();

        log.info("Successfully fetched {} orders with status : {}", responseList.size(), orderStatus);

        return responseList;
    }



    // ==========================
    // Get Orders By Payment Status
    // ==========================

    @Override
    public List<OrderResponse> getOrdersByPaymentStatus(PaymentStatus paymentStatus) {

        log.info("Received request to fetch orders with payment status : {}", paymentStatus);

        // -------------------- Step 1 : Fetch Orders from Database --------------------

        List<Order> orders = orderRepository.findByPaymentStatus(paymentStatus);

        if (orders.isEmpty()) {log.error("No orders found with payment status : {}", paymentStatus);

            throw new OrderNotFoundException("No orders found with payment status : " + paymentStatus);
        }

        // -------------------- Step 2 : Convert Entity List into Response DTO List --------------------

        List<OrderResponse> responseList = orders.stream()
                .map(this::mapToResponse)
                .toList();

        log.info("Successfully fetched {} orders with payment status : {}", responseList.size(), paymentStatus);

        return responseList;
    }

    // ==========================
    // Get Orders Between Two Dates
    // ==========================

    @Override
    public List<OrderResponse> getOrdersBetweenDates(LocalDate startDate, LocalDate endDate) {

        log.info("Received request to fetch orders between {} and {}", startDate, endDate);

        // -------------------- Step 1 : Convert LocalDate into LocalDateTime --------------------

        LocalDateTime startDateTime = startDate.atStartOfDay();

        LocalDateTime endDateTime = endDate.atTime(LocalTime.MAX);

        // -------------------- Step 2 : Fetch Orders from Database --------------------

        List<Order> orders = orderRepository.findByOrderDateBetween(startDateTime, endDateTime);

        // -------------------- Step 3 : Check if Orders Exist --------------------

        if (orders.isEmpty()) {log.error("No orders found between {} and {}", startDate, endDate);

            throw new OrderNotFoundException("No orders found between " + startDate + " and " + endDate);
        }

        // -------------------- Step 4 : Convert Entity List into Response DTO List --------------------

        List<OrderResponse> responseList = orders.stream()
                .map(this::mapToResponse)
                .toList();

        log.info("Successfully fetched {} orders between {} and {}",
                responseList.size(), startDate, endDate);

        return responseList;
    }


    // ==========================
    // Get Today's Orders
    // ==========================

    @Override
    public List<OrderResponse> getTodayOrders() {

        log.info("Received request to fetch today's orders.");

        // -------------------- Step 1 : Get Today's Date --------------------

        LocalDate today = LocalDate.now();

        // -------------------- Step 2 : Convert Today's Date into Start and End Time --------------------

        LocalDateTime startDateTime = today.atStartOfDay();

        LocalDateTime endDateTime = today.atTime(LocalTime.MAX);

        // -------------------- Step 3 : Fetch Today's Orders from Database --------------------

        List<Order> orders = orderRepository.findByOrderDateBetween(startDateTime, endDateTime);

        // -------------------- Step 4 : Check if Orders Exist --------------------

        if (orders.isEmpty()) {

            log.error("No orders found for today.");

            throw new OrderNotFoundException("No orders found for today.");
        }

        // -------------------- Step 5 : Convert Entity List into Response DTO List --------------------

        List<OrderResponse> responseList = orders.stream()
                .map(this::mapToResponse)
                .toList();

        log.info("Successfully fetched {} today's orders.", responseList.size());

        return responseList;
    }












    // -------------------- Entity to Response DTO Mapping --------------------

    private OrderResponse mapToResponse(Order order) {

        return OrderResponse.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .customerId(order.getCustomerId())
                .productId(order.getProductId())
                .quantity(order.getQuantity())
                .price(order.getPrice())
                .totalAmount(order.getTotalAmount())
                .orderStatus(order.getOrderStatus())
                .paymentStatus(order.getPaymentStatus())
                .orderDate(order.getOrderDate())
                .createdDate(order.getCreatedDate())
                .updatedDate(order.getUpdatedDate())
                .build();

    }
}


































































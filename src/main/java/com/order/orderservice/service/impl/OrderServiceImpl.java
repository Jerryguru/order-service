package com.order.orderservice.service.impl;

import com.order.orderservice.dto.OrderRequest;
import com.order.orderservice.dto.OrderResponse;
import com.order.orderservice.entity.Order;
import com.order.orderservice.enums.OrderStatus;
import com.order.orderservice.enums.PaymentStatus;
import com.order.orderservice.exception.OrderNotFoundException;
import com.order.orderservice.repository.OrderRepository;
import com.order.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.context.annotation.ConfigurationClassUtils.getOrder;

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

        return responses;

    }



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



          // ==========================
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


    // ==========================
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




















































































































































/*
package com.order.orderservice.service.impl;

// OrderServiceImpl (Business Logic Layer) We Provide  Business Logics Here

import com.order.orderservice.dto.OrderRequest;
import com.order.orderservice.dto.OrderResponse;
import com.order.orderservice.entity.Order;
import com.order.orderservice.repository.OrderRepository;
import com.order.orderservice.service.OrderService;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Service            //@Service -->tells Spring that this class contains business logic and should be managed as a Spring Bean.
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository){
        this.orderRepository=orderRepository;
    }


  */
/*
  * @Override //"This method is implementing (or overriding)
  *  an existing method from a parent class or interface."
  * *//*


    // -------------------- CreateOrder --------------------

    @Override   //This method is implementing the method declared in the OrderService interface.
    public OrderResponse createOrder(OrderRequest request) {
        // -------------------- Step 1 : Create Order Entity --------------------

        Order order = new Order();

        // -------------------- Step 2 : Map Request DTO to Entity --------------------

        order.setOrderNumber(request.getOrderNumber());

        order.setCustomerId(request.getCustomerId());

        order.setProductId(request.getProductId());

        order.setQuantity(request.getQuantity());

        order.setPrice(request.getPrice());

        order.setTotalAmount(request.getTotalAmount());

        order.setOrderStatus(request.getOrderStatus());

        order.setPaymentStatus(request.getPaymentStatus());

        order.setOrderDate(request.getOrderDate());

        order.setCreateDate(LocalDateTime.now());

        order.setUpdateDate(LocalDateTime.now());


        // -------------------- Step 3 : Save Entity into Database --------------------

        Order savedOrder = orderRepository.save(order);    //save() stores an entity in the database.

        // -------------------- Step 4 : Map Saved Entity to Response DTO --------------------

        OrderResponse response = new OrderResponse();    //We created Response Object Because to send a JSON For Client ,We should never return Entity directly to the client.

        response.setId(savedOrder.getId());

        response.setOrderNumber(savedOrder.getOrderNumber());

        response.setCustomerId(savedOrder.getCustomerId());

        response.setProductId(savedOrder.getProductId());

        response.setQuantity(savedOrder.getQuantity());

        response.setPrice(savedOrder.getPrice());

        response.setTotalAmount(savedOrder.getTotalAmount());

        response.setOrderStatus(savedOrder.getOrderStatus());

        response.setPaymentStatus(savedOrder.getPaymentStatus());

        response.setOrderDate(savedOrder.getOrderDate());


        // -------------------- Step 5 : Return Response DTO --------------------

        return response;
    }


    // -------------------- getAllOrders --------------------

    @Override
    public List<OrderResponse> getAllOrders() {     //List<OrderResponse> 👉 This method returns multiple OrderResponse objects.

        // -------------------- Step 1 : Fetch All Orders from Database --------------------

        List<Order> orders = orderRepository.findAll();

// -------------------- Step 2 : Create Response DTO List --------------------

        List<OrderResponse> responses = new ArrayList<>();

// -------------------- Step 3 : Convert Entity List to Response DTO List --------------------

        for (Order order : orders) {

            OrderResponse response = new OrderResponse();

            response.setId(order.getId());

            response.setOrderNumber(order.getOrderNumber());

            response.setCustomerId(order.getCustomerId());

            response.setProductId(order.getProductId());

            response.setQuantity(order.getQuantity());

            response.setPrice(order.getPrice());

            response.setTotalAmount(order.getTotalAmount());

            response.setOrderStatus(order.getOrderStatus());

            response.setPaymentStatus(order.getPaymentStatus());

            response.setOrderDate(order.getOrderDate());

            response.setCreateDate(order.getCreateDate());

            response.setUpdateDate(order.getUpdateDate());

            responses.add(response);
        }

// -------------------- Step 4 : Return Response DTO List --------------------

        return responses;


    }

    // -------------------- getOrderById --------------------

    @Override
    public OrderResponse getOrderById(Long id) {
        return null;
    }

    // -------------------- updateOrder --------------------

    @Override
    public OrderResponse updateOrder(Long id, OrderRequest request) {
        return null;
    }


    // -------------------- partialUpdateOrder --------------------

    @Override
    public OrderResponse partialUpdateOrder(Long id, OrderRequest request) {
        return null;
    }

    // -------------------- deleteOrder --------------------



    @Override
    public String deleteOrder(Long id) {
        return "";
    }
}
*/

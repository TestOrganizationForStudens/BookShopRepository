package com.example.demoBookShop.controllers;

import com.example.demoBookShop.exceptions.AppException;
import com.example.demoBookShop.models.Order;
import com.example.demoBookShop.models.Product;
import com.example.demoBookShop.models.ProductOrder;
import com.example.demoBookShop.models.User;
import com.example.demoBookShop.servicies.OrderService;
import com.example.demoBookShop.servicies.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/order")
@CrossOrigin
public class OrderController {
    @Autowired
    private final OrderService orderService;
    @Autowired
    private final UserService userService;

    public OrderController(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<Object> getAllOrders() {
        List<Order> orders=orderService.getAllOrders();
        return ResponseEntity.status(HttpStatus.OK).body(orders);

    }

    @GetMapping("findByPrice")
    public ResponseEntity<Object> findByPrice(@RequestParam("price") Double price){
        try {
            List<Order> orders = orderService.findByPrice(price);
            return ResponseEntity.status(HttpStatus.OK).body(orders);
        }catch (AppException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("findByUserData")
    public ResponseEntity<Object> findByUserData(@RequestParam("user") User userData){
        try {
            List<Order> orders = orderService.findByUserData(userData);
            return ResponseEntity.status(HttpStatus.OK).body(orders);
        }catch (AppException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("Date")
    public ResponseEntity<Object> findByDateTime(@DateTimeFormat (pattern = "yyyy-MM-dd HH:mm:ss") @RequestParam("dateTime") LocalDateTime dateTime){
        try {
            List<Order> orders = orderService.findByDateTime(dateTime);
            return ResponseEntity.status(HttpStatus.OK).body(orders);
        }catch (AppException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping
    @RequestMapping("{id}")
    public ResponseEntity<Object>findOrderById(@PathVariable Long id){
        Order order=orderService.findOrderById(id);
        return ResponseEntity.status(HttpStatus.OK).body(order);
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody Order order){
        try {
            orderService.create(order);
            return ResponseEntity.status(HttpStatus.OK).body(order);
        }catch (AppException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        try{
            //also need to check for children records before deleting.
            Order order =orderService.delete(id);
            return  ResponseEntity.status(HttpStatus.OK).body(order);
        }catch (AppException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Order order){
        try{
            orderService.update(id, order);
            return ResponseEntity.status(HttpStatus.OK).body(order);
        }catch (AppException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

//    @PostConstruct
//    public void fillData() {
//        Product product1= new Product(1L,"Test seed", "Test seed", "Test seed",
//                "Test seed", 2016, 29.75, "Test from Postman2",
//                8, "imagine");
//                //"data:image/jpeg;base64,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");
//
//        Product product2=  new Product(2L,"Baiatul cu pijamale in dungi", "Beletristica", "John Boyne", "Libris", 2016,  29.75,
//                "Aceasta este povestea unui baietel german pe nume Bruno, al carui tata a primit o slujba foarte importanta, ceea ce inseamna ca toata familia trebuie sa se mute departe de oras, intr-un loc ciudat, unde casa lor e singura locuinta adevarata si unde in spatele unor garduri nesfarsite se afla sute, poate mii de oameni imbracati in pijamale in dungi. ",
//                8, "imagine");
//                //"data:image/jpeg;base64,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");
//
//        Product product3=new Product(3L,"Baltagul","Beletristica","Mihail Sadoveanu","Libris",
//                2016,22.0, "Cu Baltagul, Mihail Sadoveanu se aseaza mai putin in inima literaturii romanesti, unde l-au asezat cele peste 50 de volume, ca tot atitea aspecte ale lumii careia el, povestitorul, i-a dat viata, cat in inima propriei sale literaturi. Baltagul se mentine in zona aceea superioara de mister si de poezie, inceputa cu Hanu Ancutei si continuata in buna parte de Zodia Cancerului.",
//                8, "imagine");
//                //"data:image/jpeg;base64,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");
//
//        ProductOrder productOrder1= new ProductOrder(product1, 3);
//        ProductOrder productOrder2= new ProductOrder(product2, 1);
//        ProductOrder productOrder3= new ProductOrder(product3, 5);
//        User user1 =this.userService.findUserById(1L);
//        User user2 =this.userService.findUserById(2L);
//
//        Order order1= new Order(LocalDateTime.now(), user1, 53.56);
//        order1.setProductOrderList(List.of(productOrder1, productOrder3, productOrder2));
//        this.orderService.create(order1);
//
//        Order order2= new Order(LocalDateTime.now(), user2, 100.00);
//        order1.setProductOrderList(List.of(productOrder1, productOrder2));
//        this.orderService.create(order2);
//    }
}

package com.carService.product;

import com.carService.product.entity.Order;
import com.carService.product.entity.User;
import com.carService.product.repos.OrderRepo;
import com.carService.product.repos.UserRepo;
import com.carService.product.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.Principal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private OrderRepo orderRepo;

    @Mock
    private UserRepo userRepo;

    @InjectMocks
    private OrderService orderService;

    @Test
    public void saveOrder_withValidOrder() {
        Order order = new Order();
        order.setName("Test order2");

        // возвращает сохраненный заказ
        Mockito.when(orderRepo.save(order)).thenReturn(order);

        // вызывает метод saveOrder
        orderService.saveOrder(null, order);

        //проверят что метод save вызвается с переданным заказом
        Mockito.verify(orderRepo).save(order);

        //проверяет что заказ сохранился с правильным именем
        assertThat(order.getName()).isEqualTo("Test order2");
    }

    @Test
    public void saveOrdery_withValidOrderAndUser() {

        Order order = new Order();
        order.setName("Test order");

        User user = new User();
        user.setEmail("test@example.com");

        //подделываем метод save() чтобы он возвращал сохраненный заказ
        Mockito.when(orderRepo.save(order)).thenReturn(order);
        Mockito.when(userRepo.findByEmail(user.getEmail())).thenReturn(user);

        Principal principal = new Principal() {
            @Override
            public String getName() {
                return user.getEmail();
            }
        };

        orderService.saveOrder(principal, order);

        //проверям что метод был вызван с правильным аргументом
        Mockito.verify(orderRepo).save(order);

        assertThat(order.getName()).isEqualTo("Test order");
        //проверям что пользователю присвоен заказ
        assertThat(order.getUser()).isEqualTo(user);
    }

}


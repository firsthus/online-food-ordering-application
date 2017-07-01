package demo;

import demo.domain.OrderInfo;
import demo.domain.ItemRepository;
import demo.domain.OrderRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class OrderRestControllerTest {

    // all restcontroller methods are just calling service methods, thus no need to test here
}

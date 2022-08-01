package az.task.parceldelivery.service.impl

import az.task.parceldelivery.model.dto.response.UserResp
import az.task.parceldelivery.service.AdminService
import az.task.parceldelivery.service.CourierService
import az.task.parceldelivery.service.OrderService
import az.task.parceldelivery.util.enums.OrderStatus
import mock.MockData
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification

class AdminServiceImplTest extends Specification {

    private PasswordEncoder passwordEncoder
    private OrderService orderService
    private CourierService courierService
    private AdminService adminService;

    void setup() {
        passwordEncoder = Mock()
        orderService = Mock()
        courierService = Mock()
        adminService = new AdminServiceImpl(passwordEncoder, orderService, courierService)
    }

    def "changeOrderStatus"() {
        when:
        adminService.changeOrderStatus(1, OrderStatus.CANCELED)
        then:
        1 * orderService.getOrderById(1) >> MockData.getOrderEntity()

    }

    def "getOrders"() {
        when:
        adminService.getOrders()
        then:
        1 * orderService.getOrders()
    }

    def "assignOrder"() {
        given:
        def order = MockData.getOrderEntity()
        order.setCourier(MockData.getCourierEntity())
        when:
        adminService.assignOrder(1, 1)
        then:
        1 * orderService.getOrderById(1) >> MockData.getOrderEntity()
        1 * courierService.getCourierById(1) >> MockData.getCourierEntity()
    }

    def "createCourier"() {
        given:
        def encryptedPassword = "1223567"
        def courierReq = MockData.getCourierReq()
        courierReq.getUserData().setPassword(encryptedPassword)
        def expected = UserResp.of(MockData.getCourierEntity().getId())
        when:
        def actual = adminService.createCourier(courierReq)
        then:
        1 * passwordEncoder.encode(courierReq.getUserData().getPassword()) >> encryptedPassword
        1 * courierService.createCourier(courierReq) >> MockData.getCourierEntity()
        expected == actual
    }

    def "getCouriers"() {
        when:
        adminService.getCouriers()
        then:
        1 * courierService.getCouriers()
    }
}

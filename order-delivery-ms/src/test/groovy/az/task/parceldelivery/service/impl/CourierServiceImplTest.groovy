package az.task.parceldelivery.service.impl

import az.task.parceldelivery.exception.RecordNotFoundException
import az.task.parceldelivery.mapper.CourierMapper
import az.task.parceldelivery.mapper.OrderMapper
import az.task.parceldelivery.repo.CourierRepo
import az.task.parceldelivery.service.OrderService
import mock.MockData
import spock.lang.Specification

class CourierServiceImplTest extends Specification {

    private CourierRepo courierRepo;
    private CourierMapper courierMapper;
    private OrderMapper orderMapper;
    private OrderService orderService;
    private CourierServiceImpl courierService

    void setup() {
        courierRepo = Mock()
        courierMapper = Mock()
        orderMapper = Mock()
        orderService = Mock()
        courierService = new CourierServiceImpl(courierRepo, courierMapper, orderMapper, orderService)
    }

    def "getOrders success"() {
        when:
        def actual = courierService.getOrders("1")
        then:
        1 * courierRepo.findById(1) >> Optional.of(MockData.getCourierEntity())
        1 * orderMapper.entityToDto(MockData.getCourierEntity().getOrders()) >> MockData.getOrderResp()
        actual == MockData.getOrderResp()
    }

    def "getOrders fail"() {
        when:
        courierService.getOrders("1")
        then:
        1 * courierRepo.findById(1) >> Optional.empty()
        thrown RecordNotFoundException
    }

    def "GetOrder"() {
    }

    def "ChangeOrderStatus"() {
    }

    def "GetCourierById"() {
    }

    def "GetCouriers"() {
    }

    def "CreateCourier"() {
    }
}

package org.example.wedservice.Enum;

public enum OrderStatus {
    PENDING /*Đơn hàng đang chờ xử lý*/,
    CONFIRMED/* Đơn hàng đã được xác nhận bởi hệ thống hoặc nhân viên*/,
    SHIPPED/* Đơn hàng đã được gửi đi*/,
    DELIVERED /*Đơn hàng đã giao thành công cho khách hàng.*/,
    CANCELLED,
}

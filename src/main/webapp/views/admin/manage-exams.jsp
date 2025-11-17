<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<section class="page-header">
    <h1 class="page-title">Quản lý đề thi</h1>
    <p class="page-subtitle">Tạo, sửa, xóa các đề thi</p>
</section>

<div class="card table-card">
    <div style="display:flex; justify-content: space-between; align-items:center; margin-bottom:10px;">
        <span>Danh sách đề thi</span>
        <button class="btn-primary">+ Thêm đề thi</button>
    </div>

    <table class="data-table">
        <thead>
        <tr>
            <th>#</th>
            <th>Tên đề thi</th>
            <th>Môn</th>
            <th>Số câu</th>
            <th>Thời gian</th>
            <th>Trạng thái</th>
            <th>Hành động</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>1</td>
            <td>JSP/Servlet cơ bản</td>
            <td>Web</td>
            <td>30</td>
            <td>45 phút</td>
            <td><span class="badge">Đang mở</span></td>
            <td class="actions">
                <a>Sửa</a>
                <a>Xóa</a>
            </td>
        </tr>

        <tr>
            <td>2</td>
            <td>Java OOP</td>
            <td>Java</td>
            <td>40</td>
            <td>60 phút</td>
            <td><span class="badge">Ẩn</span></td>
            <td class="actions">
                <a>Sửa</a>
                <a>Xóa</a>
            </td>
        </tr>
        </tbody>
    </table>
</div>

<%@ page import="com.trongit.dto.DistrictDTO" %>
<%--

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingListURL" value="/admin/building-list"></c:url>
<html>
<head>
    <title>Danh sách toà nhà</title>
</head>
<body>


<div class="main-content">
    <div class="main-content-inner">
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try {
                    ace.settings.check('breadcrumbs', 'fixed')
                } catch (e) {
                }
            </script>

            <ul class="breadcrumb">
                <li>
                    <i class="ace-icon fa fa-home home-icon"></i>
                    <a href="<c:url value="/admin/home"/> ">Trang chủ</a>
                </li>
                <li class="active">Danh sách Building</li>
            </ul><!-- /.breadcrumb -->
        </div>

        <div class="page-content">
            <div class="row">
                <div class="widget-box">
                    <div class="widget-header">
                        <h4 class="widget-title">Tìm toà nhà</h4>
                        <div class="widget-toolbar">
                            <a href="#" data-action="collapse">
                                <i class="ace-icon fa fa-chevron-up"></i>
                            </a>
                        </div>
                    </div>
                    <div class="widget-body">
                        <div class="widget-main">
                            <form:form commandName="modelSearch" action="${buildingListURL}" id="listForm" method="get">
                                <div class="row">
                                    <div class="form-group">
                                        <div class="col-xs-12 ">
                                            <!-- PAGE CONTENT BEGIN -->
                                            <div class="col-md-6">
                                                <label><b>Tên Toà Nhà</b></label>
                                                    <%--                                                <input type="text" id="name" name="name" class="form-control" value="${modelSearch.name}">--%>
                                                <form:input path="name" cssClass="form-control"/>
                                            </div>

                                            <div class="col-md-6">
                                                <label><b>Diện Tích sàn</b></label>
                                                <input type="number" name="floorArea" class="form-control"
                                                       value="${modelSearch.floorArea}">
                                            </div>
                                            <!-- PAGE CONTENT ENDS -->
                                        </div><!-- /.col -->
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="form-group">
                                        <div class="col-xs-12 ">
                                            <div class="col-md-4">
                                                <label><b>Quận Hiện Có</b></label>
                                                <form:select path="district" cssClass="form-control">
                                                    <option selected value="">Chọn Quận</option>
<%--                                                    <c:forEach var="item" items="${modelDistrict}">--%>
<%--                                                        <form:option value="${item.code}">${item.name}</form:option>--%>
<%--                                                    </c:forEach>--%>
                                                    <form:options items="${modelDistrict}"></form:options>
                                                </form:select>
                                            </div>
                                            <div class="col-md-4">
                                                <label><b>Phường</b></label>
                                                <form:input path="ward" cssClass="form-control"/>
                                            </div>
                                            <div class="col-md-4">
                                                <label for="street"><b>Đường</b></label>
                                                <form:input path="street" cssClass="form-control"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <!-- PAGE CONTENT BEGIN -->
                                        <div class="col-md-4">
                                            <label><b>Số Tầng Hầm</b></label>
                                            <input type="number" name="numberOfBasement" class="form-control"
                                                   value="${modelSearch.numberOfBasement}">
                                        </div>

                                        <div class="col-md-4">
                                            <label><b>Hướng</b></label>
                                            <form:input path="direction" cssClass="form-control"/>
                                        </div>

                                        <div class="col-md-4">
                                            <label><b>Hạng</b></label>
                                            <form:input path="level" cssClass="form-control"/>

                                        </div>
                                        <!-- PAGE CONTENT ENDS -->
                                    </div><!-- /.col -->
                                </div>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <!-- PAGE CONTENT BEGIN -->
                                        <div class="col-md-3">
                                            <label><b>Diện Tích Từ</b></label>
                                            <input type="number" name="rentAreaFrom" class="form-control"
                                                   value="${modelSearch.rentAreaFrom}">

                                        </div>

                                        <div class="col-md-3">
                                            <label><b>Diện Tích Đến</b></label>
                                            <input type="number" name="rentAreaTo" class="form-control"
                                                   value="${modelSearch.rentAreaTo}">

                                        </div>

                                        <div class="col-md-3">
                                            <label><b>Giá Thuê Từ</b></label>
                                            <input type="number" name="rentPriceFrom" class="form-control"
                                                   value="${modelSearch.rentPriceFrom}">

                                        </div>
                                        <div class="col-md-3">
                                            <label><b>Giá Thuê Đến</b></label>
                                            <input type="number" name="rentPriceTo" class="form-control"
                                                   value="${modelSearch.rentPriceTo}">

                                        </div>
                                        <!-- PAGE CONTENT ENDS -->
                                    </div><!-- /.col -->
                                </div>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <!-- PAGE CONTENT BEGIN -->
                                        <div class="col-md-4">
                                            <label><b>Tên Quản Lí</b></label>
                                            <form:input path="managerName" cssClass="form-control"/>

                                        </div>

                                        <div class="col-md-4">
                                            <label><b>Điện Thoại Quản Lí</b></label>
                                            <form:input path="managerPhone" cssClass="form-control"/>

                                        </div>
                                        <security:authorize access="hasRole('ADMIN')">
                                            <div class="col-md-4">
                                                <label><b>Chọn Nhân Viên Phụ Trách</b></label>
                                                <form:select path="staffID" class="form-control">
                                                    <option selected value="">Chọn nhân viên phụ trách</option>
                                                    <c:forEach var="item" items="${modelStaff}">
                                                        <form:option value="${item.id}">${item.fullName}</form:option>
                                                    </c:forEach>
                                                </form:select>
                                            </div>
                                        </security:authorize>
                                        <!-- PAGE CONTENT ENDS -->
                                    </div><!-- /.col -->
                                </div>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <div class="col-md-6">
                                            <div class="form-check">
<%--                                                <c:forEach var="item" items="${modelBuildingType}">--%>
<%--                                                    <form:checkbox id="rent" path="rentTypes" value="${item.code}"--%>
<%--                                                                   label="${item.name}" cssClass="form-check-input"/>--%>
<%--                                                </c:forEach>--%>
                                                <form:checkboxes path="rentTypes" items="${modelBuildingType}"></form:checkboxes>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-xs-12">
                                        <div class="col-md-3">
                                            <button class="btn btn-primary" id="btnSearch">Tìm Kiếm</button>
                                        </div>
                                    </div>
                                </div>
                            </form:form>
                        </div>
                    </div><!-- /.row -->

                </div>


            </div>
            <div class="row">
                <div class="col-xs-12">
                    <div class="pull-right">
                        <button id="xoaBuilding" class="btn btn-white btn-warning btn-bold" data-toggle="tooltip,modal"
                                title="Xoá Toà Nhà">
                            <i class="fa fa-trash-o" aria-hidden="true"></i>
                        </button>
                    </div>

                    <div class="pull-right">
                        <button class="btn btn-white btn-info btn-bold " data-toggle="tooltip"
                                title="Thêm Toà Nhà"
                                onclick="window.location.href='<c:url value="/admin/building-edit"/>'">
                            <i class="fa fa-plus-circle" aria-hidden="true"></i>
                        </button>
                    </div>

                </div>
            </div>
            <br/>
            <!-- Modal Xac Nhan xoa-->
            <div class="modal fade" id="myModal" role="dialog">
                <div class="modal-dialog">

                    <!-- Modal content-->
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <h4 class="modal-title">Xác nhận xoá</h4>
                        </div>
                        <div class="modal-body">
                            <p>Bạn có muốn xoá Building đã chọn ????</p>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                            <button type="button" id="btnXoa" class="btn btn-default">Xoá</button>
                        </div>
                    </div>

                </div>
            </div>
            <div class="row">

                <div class="col-xs-12">
                    <table id="simple-table" class="table table-striped table-bordered table-hover">
                        <thead>
                        <tr>
                            <th class="center">
                                <label class="pos-rel">
                                    <input type="checkbox" id="selectAll" class="ace"/>
                                    <span class="lbl"></span>
                                </label>
                            </th>
                            <th>Tên Sản Phẩm</th>
                            <th>Địa Chỉ</th>
                            <th>Tên Quản Lí</th>
                            <th>Số Điện Thoại</th>
                            <th>Diện Tích Sàn</th>
                            <th>Giá Thuê</th>
                            <th>Phí Dịch Vụ</th>
                            <th>Thao Tác</th>
                        </tr>
                        </thead>

                        <tbody>
                        <c:forEach var="item" items="${modelBuildings}">
                            <tr>
                                <td class="center">
                                    <label class="pos-rel">
                                        <input type="checkbox" class="ace" name="checkBuildings[]" value="${item.id}">
                                        <span class="lbl"></span>
                                    </label>
                                </td>
                                <td>${item.name}</td>
                                <td>${item.address}</td>
                                <td>${item.managerName}</td>
                                <td>${item.managerPhone}</td>
                                <td>${item.floorArea}</td>
                                <td>${item.rentPrice}</td>
                                <td>${item.serviceFee}</td>
                                <td>
                                    <security:authorize access="hasRole('ADMIN')">
                                        <button class="btn btn-xs btn-info" data-toggle="tooltip"
                                                title="Giao toà nhà cho nhân viên quản lí" value="${item.id}"
                                                onclick="assignmentBuilding(value)">
                                            <i class="fa fa-fire" aria-hidden="true"></i>
                                        </button>
                                    </security:authorize>

                                    <button class="btn btn-xs btn-dark" data-toggle="tooltip"
                                            title="Sửa thông tin toà nhà" value="${item.id}"
                                            onclick="editBuilding(value)">
                                        <i class="fa fa-edit" aria-hidden="true"></i>
                                    </button>
                                    <button class="btn btn-xs btn-danger" data-toggle="tooltip"
                                            title="Xoá toà nhà" value="${item.id}"
                                            onclick="deleteOneBuilding(value)">
                                        <i class="fa fa-remove" aria-hidden="true"></i>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div><!-- /.span -->


            </div>
        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->
<!-- Modal -->
<div id="assignmentBuildingModal" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Danh Sách Nhân Viên</h4>
            </div>
            <div class="modal-body">
                <form>
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th class="center">
                                <label class="pos-rel">
                                    <input type="checkbox" id="selectAll2" class="ace"/>
                                    <span class="lbl"></span>
                                </label>
                            </th>
                            <th>Full Name</th>
                        </tr>
                        </thead>
                        <tbody id="dsnv">

                        </tbody>
                    </table>
                </form>
            </div>
            <div class="modal-footer">'
                <button type="button" id="assignment" class="btn btn-default" data-dismiss="modal">Giao Toà Nhà</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>

    </div>
</div>
<script>
    let buildingAssId;

    function assignmentBuilding(value) {
        buildingAssId = value;
        $.ajax({
            type: "get",
            url: "<c:url value='/api/building/'/>" + value + '/staff',
            dataType: "json",
            success: function (response) {
                let arrBuilding = response;
                $("#dsnv").empty()
                arrBuilding.forEach(item => {
                    let ttd = '<label class="pos-rel">'
                        + '<input type="checkbox"' + item.checked + ' class="ace" name="checkStaffs[]" value="' + item.id + '">'
                        + '  <span class="lbl"></span>'
                        + '</label>'
                    let str = "<tr> <td class='center'>" + ttd + "</td> <td>" + item.fullName + "</td> </tr> "
                    $("#dsnv").append(str)
                })
                console.log(response)
            },
            error: function (res) {
                alert("fail")
            }
        });
        openModalAssignmentBuilding();
    }

    function openModalAssignmentBuilding() {
        $('#assignmentBuildingModal').modal();
    }

    $("#btnSearch").click(function (e) {
        e.preventDefault();
        $("#listForm").submit();

    })
    $("#xoaBuilding").click(function (e) {
        e.preventDefault();
        $("#myModal").modal();
    })
    let idOne;
    $("#btnXoa").click(function (e) {
        e.preventDefault();
        let values = [];
        if (idOne != null)
            values.push(idOne);
        $.each($("input[name='checkBuildings[]']:checked"), function () {
            values.push($(this).val());
        });
        $.ajax({
            url: "/api/building",
            method: "delete",
            data: JSON.stringify(values),
            contentType: "application/json",
            success: e => {
                location.reload()
            },
            error: e => {
                alert("error!!!")
            }
        })
    })

    function deleteOneBuilding(value) {
        idOne = value;
        $("#myModal").modal();
    }

    $("#assignment").click(function (e) {
        e.preventDefault();
        let values = [];
        $.each($("input[name='checkStaffs[]']:checked"), function () {
            values.push($(this).val());
        });
        let data = {
            "staffIDs": values
        }
        $.ajax({
            type: "post",
            url: '<c:url value="/api/building/"/>' + buildingAssId + '/assignment',
            data: JSON.stringify(data),
            dataType: "json",//kieu du lieu tu server tra ve client
            contentType: "application/json",//kieu du lieu tu client gui ve server
            success: function (response) {
                console.log("sucess");
                window.location.reload();
            },
            error: function (response) {
                alert("fail")
                console.log(response)
            }
        });
    })
    $("#selectAll").click(function () {
        $("input[name='checkBuildings[]']").prop('checked', $(this).prop('checked'));

    });
    $("#selectAll2").click(function () {
        $("input[name='checkStaffs[]']").prop('checked', $(this).prop('checked'));

    });

    function editBuilding(value) {
        window.location.href = "<c:url value="/admin/building-edit"/>" + "?buildingid=" + value;
    }

    if ((${modelSearch.rentTypes}) != []) {
        $.each(${modelSearch.rentTypes}, function (index, value) {
            $("#rent[value='" + value + "']").prop('checked', true);
        });
    }

</script>
</body>
</html>

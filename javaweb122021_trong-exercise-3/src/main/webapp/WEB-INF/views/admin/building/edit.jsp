<%--

--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingEditURL" value="/api/building"></c:url>
<html>
<head>
    <title>Chi tiết toà nhà</title>
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
                <li class="active">Chi tiết building</li>
            </ul><!-- /.breadcrumb -->


        </div>

        <div class="page-content">
            <div class="row">
                <div class="col-xs-12">
                    <form:form commandName="modelBuilding" cssClass="form-horizontal" role="form" id="formEdit">
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">
                                Tên toà nhà</label>
                            <div class="col-sm-9">
                                <form:input path="name" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">
                                Đường</label>
                            <div class="col-sm-9">
                                <form:input path="street" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">
                                Phường</label>

                            <div class="col-sm-9">
                                <form:input path="ward" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">
                                Quận</label>

                            <div class="col-sm-9">
                                <form:select path="district">
                                    <option selected disabled>Chọn Quận</option>
                                    --%>
                                    <form:options items="${modelDistrict}" cssClass="form-control"></form:options>
                                </form:select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">
                                Hướng</label>

                            <div class="col-sm-9">
                                <form:input path="direction" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">
                                Hạng</label>

                            <div class="col-sm-9">
                                <form:input path="level" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">
                                Cấu trúc</label>

                            <div class="col-sm-9">
                                <form:input path="structure" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">
                                Số tầng hầm</label>

                            <div class="col-sm-9">
                                <input type="number" class="form-control" name="numberOfBasement"
                                       value="${modelBuilding.numberOfBasement}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">
                                Diện tích sàn</label>
                            <div class="col-sm-9">
                                <input type="number" class="form-control" name="floorArea"
                                       value="${modelBuilding.floorArea}"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"
                            >
                                Giá thuê</label>
                            <div class="col-sm-9">
                                <form:input path="rentPrice" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"
                            >
                                Chú thích giá thuê</label>
                            <div class="col-sm-9">
                                <form:input path="rentPriceDescription" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"
                            >
                                Phí dịch vụ</label>
                            <div class="col-sm-9">
                                <form:input path="serviceFee" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"
                            >
                                Diện tích thuê</label>
                            <div class="col-sm-9">
                                <form:input path="rentArea" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"
                            >
                                Phí ô tô</label>
                            <div class="col-sm-9">
                                <form:input path="carFee" cssClass="form-control"/>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"
                            >
                                Phí mô tô</label>
                            <div class="col-sm-9">
                                <form:input path="motoFee" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"
                            >
                                Phí ngoài giờ</label>
                            <div class="col-sm-9">
                                <form:input path="overTimeFee" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"
                            >
                                Phí nước</label>
                            <div class="col-sm-9">
                                <form:input path="waterFee" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"
                            >
                                Phí điện</label>
                            <div class="col-sm-9">
                                <form:input path="electricityFee" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"
                            >
                                Tiền Cọc</label>
                            <div class="col-sm-9">
                                <form:input path="deposit" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"
                            >
                                Payment</label>
                            <div class="col-sm-9">
                                <form:input path="payment" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"
                            >
                                Thời gian thuê</label>
                            <div class="col-sm-9">
                                <form:input path="rentTime" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right"
                                   name="numberOfBasement">
                                Thời gian dercor</label>
                            <div class="col-sm-9">
                                <form:input path="decorationTime" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">
                                Tên quản lí</label>

                            <div class="col-sm-9">
                                <form:input path="managerName" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">
                                Số điện thoại quản lí</label>

                            <div class="col-sm-9">
                                <form:input path="managerPhone" cssClass="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">
                                Loại toà nhà</label>

                            <div class="col-sm-9">
                                <form:checkboxes path="type" items="${modelBuildingType}"></form:checkboxes>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label no-padding-right">
                            </label>
                            <div class="col-sm-9">
                                <button type="button" class="btn btn-primary" id="btnAddBuilding">Thêm toà
                                    nhà
                                </button>
                                <button type="button" class="btn btn-primary">Huỷ</button>
                            </div>
                        </div>
                    </form:form>

                </div>

            </div>
            <div class="row">
                <div class="col-xs-12">


                </div>
            </div>
            <br/>
            <div class="row">

                <div class="col-xs-12">

                </div><!-- /.span -->


            </div>
        </div><!-- /.page-content -->
    </div>
</div><!-- /.main-content -->
<script>
    $('#btnAddBuilding').click(function (e) {
        e.preventDefault();
        let data = {}
        let formData = $('#formEdit').serializeArray();
        let id = ${modelBuilding.id}+'';
        if ((id) != '') {
            data["id"] = id;
        }
        let buildingTypes = [];
        formData.forEach(item => {
            if (item.name == "type") {
                buildingTypes.push(item.value);
            }
            data[item.name] = item.value
        })
        data["type"] = buildingTypes;
        $.ajax({
            type: "post",
            url: '<c:url value="/api/building"/>',
            data: JSON.stringify(data),
            dataType: "json",//kieu du lieu tu server tra ve client
            contentType: "application/json",//kieu du lieu tu client gui ve server
            success: function (response) {
                window.location.href = '<c:url value="/admin/building-list" />'
            },
            error: function (response) {
                alert("fail")
                console.log(response)
            }
        });
    })
</script>
</body>

</html>

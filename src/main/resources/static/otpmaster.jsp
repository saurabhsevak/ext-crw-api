<%@page import="com.aruhat.utility.Constants"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="url" value="${pageContext.servletContext.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>OTP Masters</title>
        <jsp:include page="../../jsp/profile2.jsp" />
        <!-- Bootstrap Core CSS -->
        <link href="resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!-- MetisMenu CSS -->
        <link href="resources/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">
        <!-- DataTables CSS -->
        <link href="resources/vendor/datatables-plugins/dataTables.bootstrap.css" rel="stylesheet">
        <!-- DataTables Responsive CSS -->
        <link href="resources/vendor/datatables-responsive/dataTables.responsive.css" rel="stylesheet">
        <!-- Custom CSS -->
        <link href="resources/css/sb-admin-2.css" rel="stylesheet">
        <!-- Custom Fonts -->
        <link href="resources/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <style>
            td  input[type="image"]  {
                border: 0 none;
                height: auto;
                max-width: 10%;
                vertical-align: middle;
            }
        </style>
    </head>

    <body>
        <div id="wrapper">
            <div id="page-wrapper" style="margin: 0 !important;">
                <div class="row">
                    <div class="col-lg-12">
                        <h4 class="page-header">Document Download > 
                            <a href="${pageContext.servletContext.contextPath}/api/otpmaster">OTP Masters</a>
                        </h4>
                    </div>
                </div>
                <div class="row">
                    <div class="col-lg-6"></div>
                    <div class="col-lg-6 alert alert-danger alert-dismissable" style="display: none">
                        <b id="error"></b>
                    </div>
                    <div class="col-lg-6 alert alert-success alert-dismissable" style="display: none">
                        <b id="success"></b>
                    </div>
                </div>

                <div class="row form-inline" style="padding-bottom: 20px;float: right;">

                    <select onchange="filterTable(this);" id="otptype" class="form-control" style="margin-right: 5px;" >
                        <option value="0">ALL</option>
                        <option value="1">USED</option>
                        <option value="2">UN-USED</option>
                    </select>
                    <button type="button" onclick="clearandfillData(true);" 
                            data-backdrop="static" 
                            data-keyboard="false" 
                            class="btn btn-primary" 
                            data-toggle="modal" 
                            data-target="#addOTP">Add Otp</button>


                </div>

                <div class="row">
                    <div class="col-lg-12">
                        <div class="panel panel-default">
                            <!-- /.panel-heading -->
                            <div class="panel-body">
                                <table width="100%" class="table table-striped table-bordered table-hover" id="example">
                                    <thead>	
                                        <tr>
                                            <th>Sr no.</th>
                                            <th>OTP</th>
                                            <th>Workorder Id</th>
                                            <th>Credit Card Number</th>
                                            <th>Card Holder name</th>
                                            <th>Attempt Status</th>
                                            <th>used/not used</th>
                                            <th>Insertion Time</th>
                                            <th>Last Modified</th>
                                            <th>Actions</th>
                                        </tr>
                                    </thead>                                                                 
                                </table>
                            </div>
                            <!-- /.panel-body -->
                        </div>
                        <!-- /.panel -->
                    </div>
                    <!-- /.col-lg-12 -->
                </div>



                <!-- Modal -->
                <div class="modal fade" id="addOTP" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog" style="width: 60% !important;">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 id="popuptitle" class="modal-title">Add OTP</h4>
                            </div>
                            <div class="modal-body" style="height: 40%;overflow-y:scroll; ">
                                <form role="form" id="addOTPForm">
                                    <div class="alert alert-danger alert-dismissable" style="display: none" id="addOTPError">
                                        <span id="addOTPErrorMsg"></span>
                                    </div>
                                    <div class="form-group">
                                        <div id="creditcardselection" class="form-group form-inline" >
                                            <label>Select Phone No</label>
                                            <select id="device" style="margin-left: 3%;width: 30%;"  class="form-control"  >
                                                <option id="device0" selected="selected" value="0">--select phone no--</option>
                                            </select>
                                            <label style="margin-left: 1%;">OR</label>
                                            <label style="margin-left: 1%;" >Select Device</label>
                                            <select id="card"  class="form-control" style="margin-left: 1%;width: 35%">
                                                <option id="card0" selected="selected" value="0">--select card--</option>
                                            </select>
                                        </div>
                                        <div class="form-group form-inline">
                                            <label>Select Work-Order</label>
                                            <select id="workorder" class="form-control"  style="width: 75%;margin-left: 1%;">
                                                <option id="workorder0" selected="selected" value="0">--select WorkOrder No--</option>
                                            </select>
                                        </div>
                                        <div class="form-group form-inline">
                                            <label>OTP(6 Chars)</label>
                                            <input placeholder="OTP" maxlength="6" 
                                                   class="form-control otp"
                                                   style="text-transform: uppercase;width: 15%;margin-left: 5%;"
                                                   required="required" id="OTP" name="otp" onkeyup="onlyNumberAllowed(this);">
                                        </div>

                                    </div>

                                </form>
                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                <button type="submit" class="btn btn-primary" onclick="addOtp()">Save</button>
                            </div>
                        </div>
                        <!-- /.modal-content -->
                    </div>
                    <!-- /.modal-dialog -->
                </div>
                <!-- /.modal -->



                <div class="modal fade" id="uploadModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" style="margin-top: 15%">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <h2 class="text-center" style="padding: 30px;">Please wait....</h2>
                        </div>
                    </div>
                </div>

                <div id="allHtml" class="hidden">

                </div>


            </div>
            <!-- /#page-wrapper -->
        </div>
        <!-- /#wrapper -->
        <!-- jQuery -->
        <script src="resources/vendor/jquery/jquery.min.js"></script>
        <!-- Bootstrap Core JavaScript -->
        <script src="resources/vendor/bootstrap/js/bootstrap.min.js"></script>

        <!-- Metis Menu Plugin JavaScript -->
        <script src="resources/vendor/metisMenu/metisMenu.min.js"></script>
        <!-- DataTables JavaScript -->
        <script src="resources/vendor/datatables/js/jquery.dataTables.min.js"></script>
        <script src="resources/vendor/datatables-plugins/dataTables.bootstrap.min.js"></script>
        <script src="resources/vendor/datatables-responsive/dataTables.responsive.js"></script>
        <!-- Custom Theme JavaScript -->
        <script src="resources/js/sb-admin-2.js"></script>
        <script src="resources/js/common.js"></script>


        <!-- Page-Level Demo Scripts - Tables - Use for reference -->
        <script>

                                    var exampletable = '';
                                    $(document).ready(function () {

                                        var type = querystring("type");

                                        loadData(type[0]);
                                        setInterval(function () {
                                            exampletable.ajax.reload();
                                        }, 1000000);
                                    });

                                    function loadData(filter) {
                                        if (filter === undefined) {
                                            filter = 0;
                                        }

                                        $('#otptype').val(filter);
                                        exampletable = $('#example').DataTable({
                                            responsive: true,
                                            "processing": true,
                                            "serverSide": true,
                                            "ajax": {
                                                "url": "${pageContext.servletContext.contextPath}/api/otpmaster/lsit/",
                                                "type": "POST",
                                                "data": {
                                                    otptype: parseInt($("#otptype").val())
                                                }
                                            },
                                            columns: [
                                                {data: function (data, type, row, meta) {
                                                        var page = Math.ceil(meta.settings._iDisplayStart / meta.settings._iDisplayLength);
                                                        var totalRecordsPerPage = meta.settings._iDisplayLength;
                                                        var r = meta.row + 1;
                                                        return page * totalRecordsPerPage + r;
                                                    }
                                                },
                                                {data: "otp"},
                                                {data: "workorder_id"},
                                                {data: "credit_card_number_string"},
                                                {data: "credit_card_holder_name"},
                                                {data: function (data, type, row, meta) {
                                                        var returnstring = data.attempt_status;
                                                        return returnstring;
                                                    }
                                                },
                                                {data: function (data, type, row, meta) {
                                                        var returnstring = '-';
                                                        var isUsed = data.is_used;
                                                        if (isUsed === 1) {
                                                            returnstring = 'USED';
                                                        } else if (isUsed === 0) {
                                                            returnstring = 'NOT USED';
                                                        }
                                                        return returnstring;
                                                    }
                                                },
                                                {data: "insertion_time"},
                                                {data: "last_modified"},
                                                {data: function (data, type, row, meta) {
                                                        var returnstring = '';

                                                        if (data.attempt_page_html !== undefined) {
                                                            var failes = 'btn-danger';
                                                            if (data.attempt_status === 'Success') {
                                                                failes = 'btn-success';

                                                                var passingString = escape(data.attempt_page_html);

                                                                returnstring +=
                                                                        ' <button type="button" value="saurabh" onclick="openPageOnnewTab(\'' + passingString + '\');" class="btn' + failes + '  btn-circle btn-sm"'
                                                                        + ' ><span class="glyphicon glyphicon-new-window"></span></button> ';

//                                                                returnstring +=
//                                                                        ' <button type="button" value="saurabh" onclick=\'var w = window.open("", "_blank", ""); w.document.write(\"' + passingString + '\"); w.onload(w.document.body.innerHTML=unescape(w.document.body.innerHTML));  \' class="btn' + failes + '  btn-circle btn-sm"'
//                                                                        + ' ><span class="glyphicon glyphicon-new-window"></span></button> ';

                                                            }

                                                        }



                                                        returnstring +=
                                                                ' <button type="button" onclick="deleteOtp(' + data.otp_id + ');" class="btn btn-default btn-circle btn-sm"'
                                                                + ' ><span class="glyphicon glyphicon-remove"></span></button> ';
                                                        return returnstring;
                                                    }
                                                }
                                            ],
                                            "aoColumnDefs": [
                                                {"bSortable": false, "aTargets": ["_all"]}
                                            ]
                                        });
                                    }




                                    function clearandfillData(isRequiredToFillData) {
                                        openProcessingPopup();
                                        if (isRequiredToFillData) {
                                            fillGenratedotpWorkorderData();
                                            fillCreditCardData();
                                            fillDeviceData();
                                        }

                                        closeProcessingPopup();
                                    }


                                    function fillDeviceData() {
                                        var data = {key_name: 'devicemaster'};
                                        $.ajax({
                                            url: "${pageContext.servletContext.contextPath}/api/common/getlist/",
                                            type: "POST",
                                            data: JSON.stringify(data),
                                            contentType: "application/json",
                                            success: function (data) {
                                                if (data.status === "0") {
                                                    $('#addOTPError').show();
                                                    $('#addOTPErrorMsg').html(data.message);
                                                    setTimeout(function () {
                                                        $('#addOTPError').hide();
                                                    }, 5000);
                                                    $('.modal-body').animate({scrollTop: 0}, 800);
                                                } else if (data.status === "1") {
                                                    var masterDetails = data.data.master_detail;
                                                    if (masterDetails) {
                                                        for (var counter = 0; counter < masterDetails.length; counter++) {
                                                            $("select#device").html(
                                                                    $("select#device").html()
                                                                    + '<option id="device'
                                                                    + masterDetails[counter].device_master_id
                                                                    + '" name="'
                                                                    + masterDetails[counter].device_master_id
                                                                    + '" value="'
                                                                    + masterDetails[counter].device_master_id
                                                                    + '">'
                                                                    + masterDetails[counter].phone_no
                                                                    + '</option>'
                                                                    );
                                                        }


                                                    }
                                                }
                                            }
                                        });
                                    }



                                    function fillCreditCardData() {
                                        var data = {key_name: 'cardholder'};
                                        $.ajax({
                                            url: "${pageContext.servletContext.contextPath}/api/common/getlist/",
                                            type: "POST",
                                            data: JSON.stringify(data),
                                            contentType: "application/json",
                                            success: function (data) {
                                                if (data.status === "0") {
                                                    $('#addOTPError').show();
                                                    $('#addOTPErrorMsg').html(data.message);
                                                    setTimeout(function () {
                                                        $('#addOTPError').hide();
                                                    }, 5000);
                                                    $('.modal-body').animate({scrollTop: 0}, 800);
                                                } else if (data.status === "1") {
                                                    var masterDetails = data.data.master_detail;
                                                    if (masterDetails) {
                                                        for (var counter = 0; counter < masterDetails.length; counter++) {
                                                            $("select#card").html(
                                                                    $("select#card").html()
                                                                    + '<option id="card'
                                                                    + masterDetails[counter].credit_card_id
                                                                    + '" name="'
                                                                    + masterDetails[counter].credit_card_id
                                                                    + '" value="'
                                                                    + masterDetails[counter].credit_card_id
                                                                    + '">'
                                                                    + masterDetails[counter].name
                                                                    + ' ('
                                                                    + masterDetails[counter].credit_card_number_string
                                                                    + ')'
                                                                    + '</option>'
                                                                    );
                                                        }


                                                    }
                                                }
                                            }
                                        });
                                    }


                                    function fillGenratedotpWorkorderData() {
                                        var data = {key_name: 'genratedotpworkorder'};
                                        $.ajax({
                                            url: "${pageContext.servletContext.contextPath}/api/common/getlist/",
                                            type: "POST",
                                            data: JSON.stringify(data),
                                            contentType: "application/json",
                                            success: function (data) {
                                                if (data.status === "0") {
                                                    $('#addOTPError').show();
                                                    $('#addOTPErrorMsg').html(data.message);
                                                    setTimeout(function () {
                                                        $('#addOTPError').hide();
                                                    }, 5000);
                                                    $('.modal-body').animate({scrollTop: 0}, 800);
                                                } else if (data.status === "1") {
                                                    var masterDetails = data.data.master_detail;
                                                    if (masterDetails) {
                                                        for (var counter = 0; counter < masterDetails.length; counter++) {
                                                            $("select#workorder").html(
                                                                    $("select#workorder").html()
                                                                    + '<option id="workorder'
                                                                    + masterDetails[counter].workorder_id
                                                                    + '" name="'
                                                                    + masterDetails[counter].workorder_id
                                                                    + '" value="'
                                                                    + masterDetails[counter].workorder_id
                                                                    + '">'
                                                                    + masterDetails[counter].company_name
                                                                    + '('
                                                                    + masterDetails[counter].workorder_id
                                                                    + ')'
                                                                    + '</option>'
                                                                    );
                                                        }


                                                    }
                                                }
                                            }
                                        });
                                    }


                                    function filterTable(elements) {
                                        var filter = $(elements).val();
                                        window.location.href = window.location.href.replace(/[\?#].*|$/, "?type=" + filter);
                                    }

//                                    $('#column3_search').on('keyup', function () {
//                                        table
//                                                .columns(3)
//                                                .search(this.value)
//                                                .draw();
//                                    });

                                    function openPageOnnewTab(htmlString) {
                                        var myWindow = window.open("", "_blank", "");
                                        myWindow.document.write(htmlString);
                                        myWindow.onload(myWindow.document.body.innerHTML = unescape(myWindow.document.body.innerHTML));
                                    }


                                    function deleteOtp(id) {
                                        if (confirm("Do you want to Delete Otp ?")) {
                                            var data = {otp_id: id};
                                            openProcessingPopup();
                                            $.ajax({
                                                url: "${pageContext.servletContext.contextPath}/api/systemparam/delete",
                                                type: "POST",
                                                data: JSON.stringify(data),
                                                contentType: "application/json",
                                                success: function (data) {
                                                    if (data.status === "0") {
                                                        closeProcessingPopup();
                                                        $('.alert-danger').css("display", "block");
                                                        $('#error').html(data.message);
                                                        setTimeout(function () {
                                                            $('.alert-danger').hide();
                                                        }, 5000);
                                                    } else if (data.status === "1") {
                                                        $('.alert-success').css("display", "block");
                                                        $('#success').html(data.message);
                                                        $("#example").dataTable().fnDraw();
                                                        clearData();
                                                        closeProcessingPopup();
                                                        setTimeout(function () {
                                                            $('.alert-success').hide();
                                                        }, 5000);
                                                    }
                                                }
                                            });

                                        }
                                    }

                                    //Create System Parameter
                                    function addOtp() {
                                        var deviceId = parseInt($('#device').val());
                                        var cardHolderID = parseInt($('#card').val());
                                        var paramId = parseInt($('#workorder').val());
                                        var otp = parseInt($('#OTP').val());
                                        var data = {
                                            device_id: deviceId,
                                            cardholder_id: cardHolderID,
                                            workorder_id: paramId,
                                            otp: otp
                                        };
                                        openProcessingPopup();
                                        $.ajax({
                                            url: "${pageContext.servletContext.contextPath}/api/systemparam/save",
                                            type: "POST",
                                            data: JSON.stringify(data),
                                            contentType: "application/json",
                                            success: function (data) {
                                                if (data.status === "0") {
                                                    closeProcessingPopup();
                                                    $('#addsystemparamsError').show();
                                                    $('#addsystemparamsErrorMsg').html(data.message);
                                                    setTimeout(function () {
                                                        $('#addsystemparamsError').hide();
                                                    }, 5000);
                                                    $('.modal-body').animate({scrollTop: 0}, 800);
                                                } else if (data.status === "1") {
                                                    $('#createList').modal('hide');
                                                    $('.alert-success').css("display", "block");
                                                    $('#success').html(data.message);
                                                    $("#example").dataTable().fnDraw();
                                                    clearData();
                                                    closeProcessingPopup();
                                                    $('#listMasterId').val(data.data.param_id);
                                                    defineColumnPopup();
                                                    setTimeout(function () {
                                                        $('.alert-success').hide();
                                                    }, 5000);
                                                }
                                            }
                                        });
                                        $('html,body').animate({scrollTop: 0}, 800);
                                    }


                                    //Edit System Parameter
                                    function openEditPopup(id) {
                                        $('#parameterId').val(id);
                                        var data = {param_id: id};
                                        openProcessingPopup();
                                        $.ajax({
                                            url: "${pageContext.servletContext.contextPath}/api/systemparam/getDetail/",
                                            type: "POST",
                                            data: JSON.stringify(data),
                                            contentType: "application/json",
                                            success: function (data) {
                                                if (data.status === "0") {
                                                    $('#addsystemparamsError').show();
                                                    $('#addsystemparamsErrorMsg').html(data.message);
                                                    setTimeout(function () {
                                                        $('#addsystemparamsError').hide();
                                                    }, 5000);
                                                } else if (data.status === "1") {
                                                    $('#parameterName').val(data.data.param_name);
                                                    $('#parameterValue').val(data.data.param_value);
                                                    $('#parameterId').val(data.data.param_id);
                                                    $('#addsystemparams').modal('show');
                                                }
                                                closeProcessingPopup();
                                            }
                                        });
                                    }


        </script>
        <style type="text/css">
            .error{border: red 1px solid};
        </style>


    </body>

</html>




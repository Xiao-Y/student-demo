<html>
<body>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/jquery-1.11.0.min.js"></script>

<h2>Hello World!</h2>
<img id="QrGen" src="" />
</body>
<script type="text/javascript">  
 $(document).ready(function() {
    var uuid;
    $.get("${pageContext.request.contextPath }/QrGen/showQrGen", function(data, status) {
        var obj = eval("(" + data + ")");
        //设置该uuid值
        uuid = obj.uuid;
        //设置二维码图片地址
        $("#QrGen").attr("src", "${pageContext.request.contextPath }/" + obj.img);
        //检查验证登录
        checkScan();
    });

    function checkScan() {
        setInterval(function() {
            $.get("${pageContext.request.contextPath }/QrGen/checkScan?uuid=" + uuid,
                   function(data, status) {
                       if (data == "ok") {
                           //验证成功并重定向到welcome页面
                           window.location = "welcome.jsp";
                       }
                   });
        },4000)
    }

});
</script>
</html>

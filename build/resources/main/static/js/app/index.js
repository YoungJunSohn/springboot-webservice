var index = {
    init : function () {
        var _this = this;
        $('#btn-save').on("click", function () {
            _this.save();
        });

        $('#btn-update').on("click", function () {
            _this.update();
        });

        $('#btn-delete').on("click", function () {
            _this.delete();
        })
    },//init fn

    save : function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };

        $.ajax({
            type:'POST',
            url : '/api/v1/posts',
            dataType : 'json',
            contentType : 'application/json; charset=utf-8',
            data:JSON.stringify(data)
        }).done(function () {
            alert('글이 등록되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });//ajax
    },//save fn

    update : function () {
        var data = {
            title:$('#title').val(),
            content:$('#content').val()
        };
        var id = $('#id').val();

        $.ajax({
            type :'PUT',
            url : '/api/v1/posts/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data : JSON.stringify(data)
        }).done(function () {
            alert("글이 수정되었습니다.");
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })//ajax
    },//update fn

    delete : function () {
        var id = $('#id').val();

        $.ajax({
            type : 'DELETE',
            dataType : 'json',
            url: '/api/v1/posts/'+id,
            contentType : 'application/json; charset=utf-8'
        }).done(function () {
            alert("삭제되었습니다.")
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })//ajax
    }//delete fn
}//main declaration

index.init();
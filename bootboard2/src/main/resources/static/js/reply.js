/*
* 댓글 구현
*/
let replyObject = {
    init: function (){
        $("#btn-save-reply").click(()=>{
            this.insertReply() //this(크릭한 대상) - 필수입력
        })
    },

    insertReply: function (){
        alert("댓글 등록 요청함");
        let boardId = $("#boardId").val();
        //document.getElementById(replyContent).value
        let replyContent = $("#replycContent").val();
        if(replyContent == ""){
            alert("댓글을 입력해주세요.");
            $("#replycContent").focus();
            return false;
        }
        let reply ={
            content: replyContent //content - 컨트롤러 넘겨주는 데이터
        }

        let header = $("meta[name='_csrf_header']").attr('content');
        let token = $("meta[name='_csrf']").attr('content');

        $.ajax({
            type: "POST",
            url: "/reply/" + boardId,
            beforeSend: function(xhr){
                xhr.setRequestHeader(header, token);
            },
            data: JSON.stringify(reply), //자바스크립트 객체를 문자화해서 JSON으로 변형
            contentType: "application/json; charset=utf-8",
        }).done(function (response){
            console.log(response);
            replyContent ="";
            location.reload(); //자동 새로고침
        }).fail(function (error){
            alert("에러 발생" + error);
        })
    }, //insertReply

    deleteReply: function (boardId, replyId){
        alert("글 삭제 요청됨");

        let header = $("meta[name='_csrf_header']").attr('content');
        let token = $("meta[name='_csrf']").attr('content');

        $.ajax({
            type: "DELETE",
            beforeSend: function(xhr){
                xhr.setRequestHeader(header, token);
            },
            url: "/reply/" + replyId,
        }).done(function (response){
            console.log(response);
            location.reload();
        }).fail(function (error){
           alert("에러발생 : " + error)
        });
    }
}
replyObject.init(); //init 함수 호출
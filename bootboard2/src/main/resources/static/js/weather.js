
/* 날짜 데이터 연동 */

//날짜를 변수화
const date = new Date();
console.log(date);
let year = '0' + date.getFullYear();
year = year.substring(1);
let month = '0' + date.getMonth() + 1; //0월부터 시작이라 +1
month = month.substring(1); //인덱스부터 끝까지 추출
let day = '0' + date.getDate();
day = day.substring(1);
let today = year + month + day;
$.ajax({
    type: "GET",
    url: "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?serviceKey=cWq32bGv6s%2BYXyuviPtmB%2BnsNsq7yi9oKktaYE7NvEuuNW280DC%2BuwD69iQhnRtNu6W8QFVoBi%2FigywhpOU6qQ%3D%3D&pageNo=1&numOfRows=1000&dataType=JSON&base_date="+ today +"&base_time=0600&nx=55&ny=127",
    success: function (data){
        console.log(data);
        console.log(data.response.body.items.item[3].obsrValue);
        let item = data.response.body.items.item[3];
        let content ="날짜: " + item.baseDate + ", 발표시각: " + item.baseTime + ", 기온: " + item.obsrValue;
        $('.result').text(content);
    },
    error: function (response){

    }
});
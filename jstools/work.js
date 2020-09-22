const fetch = require('node-fetch');
fetch("https://yx.tsp189.com/xyyx/dorm/dorm_ajust_data_provider.shtml?pid=&", {
    "headers": {
        "accept": "application/json, text/javascript, */*; q=0.01",
        "accept-language": "zh,zh-CN;q=0.9,en-US;q=0.8,en;q=0.7",
        "content-type": "application/x-www-form-urlencoded; charset=UTF-8",
        "sec-fetch-dest": "empty",
        "sec-fetch-mode": "cors",
        "sec-fetch-site": "same-origin",
        "x-requested-with": "XMLHttpRequest",
        "cookie": "JSESSIONID=AFDDD2346607153A0F952972DDC59570; collegeId=3; dormitory_login=false"
    },
    "referrer": "https://yx.tsp189.com/xyyx/dorm/adjust_data.shtml",
    "referrerPolicy": "strict-origin-when-cross-origin",
    "body": "_search=false&nd=1600398940315&rows=20&page=2&sidx=&sord=asc",
    "method": "POST",
    "mode": "cors"
}).then(respone => respone.json())
    .then(data => console.log(data)).then(function (data) {
    var parse = JSON.parse(data);
    console.log(parse);
});
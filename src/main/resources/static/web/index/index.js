function modelTest() {
    var formData = new FormData();
    formData.append("file", document.getElementById("fileInput").files[0]);
    $.ajax({
        url: "/rest/data",
        data: formData,
        type: "POST",
        beforeSend: function (XMLHttpRequest) {
            $("#segamentationLoading").show();
        },
        timeout: 0,
        contentType: false,//这里
        processData: false,//这两个一定设置为false
        success: function (data) {
            ajaxMessageReader(data, function (data) {
                appendURLFile(data['url'], "#resultList",data['eta']);
                alert("Result has been returned!");
            })
        },
        complete: function (XMLHttpRequest, textStatus) {
            $("#segamentationLoading").hide();
        }
    });
}

//点击本地上传文件
$('#btn').click(() => {
    $('#fileInput').click();
})
$('#fileInput').change((event) => {
    var files = event.target.files;
    appendFile(files, '#testList');
})


//拖拽上传文件 在页面进行预览 上传form用到ajax
const dragbox = document.querySelector('.dragFile');
dragbox.addEventListener('dragover', function (e) {
    e.preventDefault(); // 必须阻止默认事件
}, false);
dragbox.addEventListener('drop', function (e) {
    e.preventDefault(); // 阻止默认事件
    var files = e.dataTransfer.files; //获取文件
    document.getElementById("fileInput").files = files;
    appendFile(files, '#testList')
    // code
}, false);

function appendFile(files, listName) {
    for (file of files) {
        let url = window.URL.createObjectURL(file);
        let liStr = `
            <li class="list-group-item">
              <div>
                <img src="${url}" alt="文件" />
              </div>
              <h6>` + file.name + `</h6>
              <h6 style="font-size:10px">Size: ` + file.size + ` B</h6>
            </li>
          `;
        $(listName)[0].innerHTML = liStr;
    }
}

function appendURLFile(url, listName,eta) {
    let liStr = `
            <li class="list-group-item">
              <div>
                <img src="` + url + `" alt="file" />
              </div>
               <h6 style="font-size:10px">ETA: ` + eta + ` ms</h6>
            </li>
          `;
    $(listName)[0].innerHTML = liStr;
}


var mydata;

function sendQuery() {
    $.ajax({
        url: '/rest/query',
        contentType: 'application/json',
        dataType: "json",
        type: "POST",
        timeout: 0,
        data: JSON.stringify({
            "query": $("#query").val(),
            "type": $("#query-type option:selected").val()
        }),
        success: function (data) {
            mydata = data.data.records;

            var table = $("#resultTable");
            var header = "<thead>";
            if(mydata.length<0){
                return;
            }
            for(var k in mydata[0].fields){
                header += "<th>"+k+"</th>"
            }
            header += "<th>"+"Annotation"+"</th>"
            header+= "</thead>";
            table.append(header);


            for(var d in mydata){
                var tr ="<tr>";

                for(var k in mydata[d].fields){
                    tr+="<td>"+ mydata[d].fields[k]+"</td>";
                }
                tr+="<td>"+ 0.5+"</td>";
                tr+= "</tr>";
                table.append(tr);
            }
            $("#segamentationLoading").hide();
            table.show();

        }
    })
}

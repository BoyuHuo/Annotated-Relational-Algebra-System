getDatalist();

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
            getDatalist();
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




function sendQuery() {

    let outputAsFile = !document.getElementById('output-type').parentElement.classList.contains("off");

    $.ajax({
        url: '/rest/query',
        contentType: 'application/json',
        dataType: "json",
        type: "POST",
        timeout: 0,
        data: JSON.stringify({
            "query": $("#query").val(),
            "type": $("#query-type option:selected").val(),
            "fileAsResult": outputAsFile
        }),
        success: function (data) {
            var mydata = data.data.records;

            var table = $("#resultTable");
            var header = "<thead>";
            if(mydata.length<0){
                return;
            }
            for(var k in mydata[0].fields){
                header += "<th>"+k+"</th>"
            }
            header+= "</thead>";
            table.append(header);


            for(var d in mydata){
                var tr ="<tr>";

                for(var k in mydata[d].fields){
                    tr+="<td>"+ mydata[d].fields[k]+"</td>";
                }
                tr+= "</tr>";
                table.append(tr);
            }
            $("#segamentationLoading").hide();
            table.show();

        }
    })
}

function getDatalist() {
    $.ajax({
        url: '/rest/data/datalist',
        contentType: 'application/json',
        dataType: "json",
        type: "GET",
        timeout: 0,
        success: function (data) {
            var mydata = data;
            var datalist = $("#datalist").empty();
            if(data.data.length > 0){
                for(var d in data.data){
                    datalist.append(" <li class=\"list-group-item\">"+data.data[d]+"</li>");
                }
            }else{
                datalist.append("  <li class=\"list-group-item\">No Data</li>");
            }
        }
    })
}

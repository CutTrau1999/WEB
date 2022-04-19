document.addEventListener('DOMContentLoaded', function () {
    // lấy table body để thay đổi
    var url_string = window.location.href.toLowerCase();
    var url = new URL(url_string);
    var id = url.searchParams.get('id');
    var tableBody = document.getElementById('my-table-data');
    var xmlHttpRequest = new XMLHttpRequest();
    // sự kiện khi request thay đổi trạng thái
    if (id != undefined && id.length > 0) {
        isEdit = true;
        let xmlHttpRequest = new XMLHttpRequest();
        xmlHttpRequest.onreadystatechange = function () {
            if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
                var data = JSON.parse(xmlHttpRequest.responseText);
                var newContent = '';
                    newContent += `
                    <h2>${data.name}</h2>
                    <div  style="width: 100%; height: 0px; position: relative; padding-bottom: 56.250%;"><iframe src="${data.link}" frameborder="0" width="100%" height="100%" allowfullscreen style="width: 100%; height: 100%; position: absolute;"></iframe></div>

                    
                `;

                tableBody.innerHTML = newContent; // thay đổi nội dung table.
            }
        };
        xmlHttpRequest.open('get', 'http://localhost:8088/api/videos/' + id, false);
        xmlHttpRequest.send();
    }

    // kiểm tra nếu click vào btn delete thì sẽ delete Product đó đi
    document.body.addEventListener('click', function (event) {
        if (event.target.className === 'btn-delete') {
            if (confirm('Are you sure you want to delete?')) {
                let id = event.target.title;
                const xmlHttpRequest = new XMLHttpRequest();
                xmlHttpRequest.onreadystatechange = function () {
                    if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
                        alert('Deleted successfully');
                        location.reload();
                    }
                };
                xmlHttpRequest.open(
                    'delete',
                    'http://localhost:8088/api/products/' + id,
                    false
                );
                xmlHttpRequest.send();
            }
        }
    });
});

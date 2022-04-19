document.addEventListener('DOMContentLoaded', function () {
    // lấy table body để thay đổi
    var tableBody = document.getElementById('my-table-data');

    var xmlHttpRequest = new XMLHttpRequest();

    xmlHttpRequest.onreadystatechange = function () {

        if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
            var data = JSON.parse(xmlHttpRequest.responseText);
            var newContent = '';
            for (let i = 0; i < data.length; i++) {
                newContent += `
                <tr>
                    <td>${data[i].name}</td>
                    
                    <td><div  style="width: 100%; height: 0px; position: relative; padding-bottom: 56.250%;"><iframe src="${data[i].link}" frameborder="0" width="100%" height="100%" allowfullscreen style="width: 100%; height: 100%; position: absolute;"></iframe></div></td>
                    <td>
                        <button>
                        <a href="details.html?id=${data[i].id}" class="btn-edit">Watch Now!</a>
                        </button>

<!--                        <button href="#" class="bt">Add to Cart</button>-->
                    </td>
                </tr>`;
            }
            tableBody.innerHTML = newContent; // thay đổi nội dung table.
        }
    };
    xmlHttpRequest.open('get', 'http://localhost:8088/api/videos', false);
    xmlHttpRequest.send();

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

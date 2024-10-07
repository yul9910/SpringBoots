document.addEventListener("DOMContentLoaded", async function() {
    try {
        // Load header.html into #header-placeholder
        const headerResponse = await fetch("/common/header.html");
        if (!headerResponse.ok) {
            throw new Error("헤더를 불러오는 중 오류가 발생했습니다.");
        }
        const headerData = await headerResponse.text();
        document.getElementById("header-placeholder").innerHTML = headerData;

        // Load order list data and render
        await loadOrderList();
    } catch (error) {
        console.error("헤더를 로드할 수 없습니다:", error);
    }
});

async function loadOrderList() {
    try {
        const response = await fetch(`/api/orders`);
        if (!response.ok) {
            throw new Error('주문 목록을 가져오는 중 오류가 발생했습니다.');
        }
        const orders = await response.json();
        renderOrderList(orders);
    } catch (error) {
        document.getElementById('order-list-root').innerHTML = `<div class="notification is-danger">${error.message}</div>`;
    }
}


function renderOrderList(orders) {
    const orderListRoot = document.getElementById('order-list-root');

    if (!orders || orders.length === 0) {
        orderListRoot.innerHTML = "<p>주문 내역이 없습니다.</p>";
        return;
    }

    let ordersHtml = `
        <h2 class="title is-4 has-text-centered">주문 내역</h2>
        <table class="table is-fullwidth is-striped">
            <thead>
                <tr>
                    <th>주문 번호</th>
                    <th>주문 날짜</th>
                    <th>상품</th>
                    <th>주문 금액</th>
                    <th>상태</th>
                    <th>액션</th>
                </tr>
            </thead>
            <tbody>
    `;

    orders.forEach(order => {
        ordersHtml += `
            <tr>
                <td><a href="/order-details/order-details.html?id=${order.ordersId}">${order.ordersId}</a></td>
                <td>${new Date(order.createdAt).toLocaleDateString()}</td>
                <td>${order.quantity}개</td>
                <td>₩${order.ordersTotalPrice}</td>
                <td>${order.orderStatus}</td>
                <td>
                    <a class="button is-small is-link" href="/order-details/order-details.html?id=${order.ordersId}">상세 보기</a>
                    ${
            order.orderStatus === '주문완료'
                ? `<button class="button is-small is-danger ml-2" onclick="cancelOrder(${order.ordersId})">주문 취소</button>`
                : ''
        }
                </td>
            </tr>
        `;
    });

    ordersHtml += `
            </tbody>
        </table>
    `;

    orderListRoot.innerHTML = ordersHtml;
}

function cancelOrder(orderId) {
    // 주문 취소 로직
    if (confirm("주문을 취소하시겠습니까?")) {
        fetch(`/api/orders/${orderId}`, { method: 'DELETE' }) // DELETE 메소드로 변경
            .then(response => {
                if (!response.ok) {
                    throw new Error("주문 취소 중 오류가 발생했습니다.");
                }
                alert("주문이 취소되었습니다.");
                loadOrderList(); // 주문 목록을 새로 로드
            })
            .catch(error => {
                alert("주문을 취소할 수 없습니다: " + error.message);
            });
    }
}

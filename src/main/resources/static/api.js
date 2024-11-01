
async function get(endpoint, params = "") {
  const apiUrl = params ? `${endpoint}/${params}` : endpoint;
  console.log(`%cGET 요청: ${apiUrl} `, "color: #a25cd1;");

  // 토큰이 있으면 Authorization 헤더를 포함, 없으면 포함하지 않음
//  const token = sessionStorage.getItem("token");
//  const headers = token ? { Authorization: `Bearer ${token}` } : {};

  //쿠키
  const res = await fetch(apiUrl, {
    credentials: "include", // 쿠키 포함
  });

//  const res = await fetch(apiUrl, { headers });

  // 응답 코드가 4XX 계열일 때 (400, 403 등)
  if (!res.ok) {
    try {
      const errorContent = await res.json();
      const { reason } = errorContent;
      throw new Error(reason);
    } catch (err) {
      throw new Error("서버 오류가 발생했습니다. 관리자에게 문의하세요.");
    }
  }

  const result = await res.json();
  return result;
}

async function post(endpoint, data) {
  const apiUrl = endpoint;
  const bodyData = JSON.stringify(data);


  // 토큰이 있으면 Authorization 헤더를 포함, 없으면 포함하지 않음
//  const token = sessionStorage.getItem("token");
//  const headers = {
//    "Content-Type": "application/json",
//    ...(token && { Authorization: `Bearer ${token}` }),
//  };

   //쿠키
   const headers = {
     "Content-Type": "application/json",
   };

  const res = await fetch(apiUrl, {
    method: "POST",
    headers,
    body: bodyData,
    credentials: "include",
  });

  // 응답 코드가 4XX 계열일 때 (400, 403 등)
  if (!res.ok && !res.created) {
    const errorContent = await res.json();
    const { reason } = errorContent;

    throw new Error(reason);
  }

  const result = await res.json();

  return result;
}


// api 로 PATCH 요청 (/endpoint/params 로, JSON 데이터 형태로 요청함)
async function patch(endpoint, params = "", data) {
  const apiUrl = params ? `${endpoint}/${params}` : endpoint;

  const bodyData = JSON.stringify(data);
  console.log(`%cPATCH 요청: ${apiUrl}`, "color: #059c4b;");
  console.log(`%cPATCH 요청 데이터: ${bodyData}`, "color: #059c4b;");

//  const res = await fetch(apiUrl, {
//    method: "PATCH",
//    headers: {
//      "Content-Type": "application/json",
//      Authorization: `Bearer ${sessionStorage.getItem("token")}`,
//    },
//    body: bodyData,
//  });

  const res = await fetch(apiUrl, {
    method: "PATCH",
    headers: {
      "Content-Type": "application/json",
    },
    body: bodyData,
    credentials: "include", // 쿠키 포함
  });

  // 응답 코드가 4XX 계열일 때 (400, 403 등)
  if (!res.ok) {
    const errorContent = await res.json();
    const { reason } = errorContent;

    throw new Error(reason);
  }

  const result = await res.json();

  return result;
}

// 아래 함수명에 관해, delete 단어는 자바스크립트의 reserved 단어이기에,
// 여기서는 우선 delete 대신 del로 쓰고 아래 export 시에 delete로 alias 함.
async function del(endpoint, params = "", data = {}) {
  const apiUrl = params ? `${endpoint}/${params}` : endpoint;
  // 끝에 있는 슬래시 제거
  const trimmedUrl = apiUrl.replace(/\/$/, '');
  const bodyData = JSON.stringify(data);

  console.log(`DELETE 요청 ${trimmedUrl}`, "color: #059c4b;");
  console.log(`DELETE 요청 데이터: ${bodyData}`, "color: #059c4b;");

//  const res = await fetch(apiUrl, {
//    method: "DELETE",
//    headers: {
//      "Content-Type": "application/json",
//      Authorization: `Bearer ${sessionStorage.getItem("token")}`,
//    },
//    body: bodyData,
//  });

  const res = await fetch(trimmedUrl, {
    method: "DELETE",
    headers: {
      "Content-Type": "application/json",
    },
    body: bodyData,
    credentials: "include", // 쿠키 포함
  });

  // 응답 코드가 4XX 계열일 때 (400, 403 등)
  if (!res.ok) {
    const errorContent = await res.json();
    throw new Error(`HTTP error! status: ${res.status}, message: ${errorContent.reason || res.statusText}`);
  }

  if (res.status === 204) {  // No Content
    return { status: res.status };
  }

  const result = await res.json();

  return result;
}

async function postFormData(endpoint, formData) {
  const apiUrl = endpoint;
  console.log(`%cPOST FormData 요청: ${apiUrl}`, "color: #059c4b;");

  // FormData 내용 로깅
  for (let [key, value] of formData.entries()) {
    console.log(`${key}:`, value);
  }

  try {
    const res = await fetch(apiUrl, {
      method: "POST",
      body: formData,
      credentials: "include",
    });

    if (!res.ok) {
      const errorContent = await res.json();
      console.error("Error response:", errorContent);
      throw new Error(errorContent.message || "서버 오류가 발생했습니다.");
    }

    return await res.json();
  } catch (error) {
    console.error("Fetch error:", error);
    throw error;
  }
}

async function patchFormData(endpoint, formData) {
  const apiUrl = endpoint;
  console.log(`%cPATCH FormData 요청: ${apiUrl}`, "color: #059c4b;");

  try {
    const res = await fetch(apiUrl, {
      method: "PATCH",
      body: formData,
      credentials: "include",
    });

    if (!res.ok) {
      const errorData = await res.json();
      throw new Error(errorData.message || "서버 오류가 발생했습니다.");
    }

    return await res.json();
  } catch (error) {
    console.error("Fetch error:", error);
    throw error;
  }
}


// 아래처럼 export하면, import * as Api 로 할 시 Api.get, Api.post 등으로 쓸 수 있음.
export { get, post, patch, del as delete, postFormData, patchFormData };

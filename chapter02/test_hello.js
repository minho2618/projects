import http from "k6/http";

export const options = {
    vus: 100, // 가상 유저 설정
    duration: "10s" // 몇 초 동안 테스트를 진행할 지
};

export default function () {
    http.get("http://localhost:8000"); // 성능 테스트 시 실행될 함수
}
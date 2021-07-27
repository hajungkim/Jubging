<template>
    <div>
        <div id="header">
            <div class="goback to-center">
                <img src="" alt="뒤로가기">
            </div>
            <div class="to-center">
                <img src="" alt="줍깅 로고">
            </div>
        </div>
        <div id="body">
            <div class="item">
                <div><h3>줍깅 후기 작성하기</h3></div>
                <textarea 
                    name="text" 
                    placeholder = "본문에 #을 이용하여 태그를 사용해보세요"
                    id="ta" 
                    cols="44" 
                    rows="14"
                ></textarea>
            </div>
            <div class="item">
                <div id="photo">
                    <div class="item-grid">+</div>
                </div>
            </div>
            <button class="btn">올리기 ></button>
        </div>

    </div>
</template>

<script>
export default {

}
</script>

<style scoped>
#header {
    position: relative;
    width: 100%;
    height: 50px;
    background-color: white;
}
#body {
    display: flex;
    margin: 0 16px;
    flex-direction: column;
    justify-content: space-around;
    height: 731px;
    background-color: white;
}
#ta {
    border-radius: 20px;
    padding: 20px;
}
#photo {
    display: flex;
    flex-wrap: wrap;
}
.to-center {
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
}
.goback {
    position: absolute;
    left: 0;
}
.item {
    text-align: center;
}
.item-grid {
    width: 80px;
    height: 80px;
    margin: 10px;
    line-height: 80px;
    border-radius: 20px;
    font-weight: bold;
    font-size: 14px;
    border: 2px solid black;
}
.bolder {
    font-weight: bold;
    font-size: 32px;
}
.btn {
    padding: 20px;
    margin: 10px 30px;
    border-radius: 30px;
    font-weight: bold;
    font-size: 18px;
}
</style>
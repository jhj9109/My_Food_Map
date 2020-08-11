<template>
<div class="container">
    <!-- 기존 주소 설정 삭제 / 검색 창 추가 -->
      <v-text-field
        v-model="message"        
        @enter="search"
        solo-inverted
        flat
        style="margin-top: 5px;"
        hide-details
        label="맛집 혹은 유저 정보를 검색해보세요."
        type="text"
        >      
      </v-text-field>
      <!-- 서치 버튼 삭제 & 클릭에서 엔터로 이벤트 변환-->
      <!--
      <template v-slot:append-outer>
          <v-btn
           @click="search"
          >
          <v-icon left>mdi-search</v-icon>
             search
          </v-btn>
        </template>
        -->

      <v-spacer></v-spacer>
      <hr style="margin-top: 10px; margin-bottom: 10px;">
    <div>
        시도 : <select id="sido" v-model="sidovalue" @change="initgugun(sidovalue)">
	    <option v-for="item in sido" v-bind:key="item.sido_code" >{{item.sido_name}}</option>
        </select>
        구군 : <select id="gugun" v-model="gugunvalue"  @change="initdong(gugunvalue)">
	<option v-for="item in gugun" v-bind:key="item.gugun_code" >{{item.gugun_name}}</option>
    </select>
    읍면동 :
<select id="dong" v-model="dongvalue" @change="initlist(dongvalue)" @click="clearMap">
    <option v-for="item in dong" v-bind:key="item.dong">{{item.dong}}</option>
</select>
  
    </div>
      <br>
<div>
    <v-row align="center">
      <v-col class="text-center" cols="12" sm="4">
          <v-btn text large color="primary" @click="onclick">내 위치</v-btn>
        <v-card-text> 경도:{{lat}}<br>위도:{{lon}} </v-card-text>        
        <!-- 모바일 페이지 기준 맵 사이즈 조정, 라운드 처리 -->
        <div id="map" class="rounded-lg" style="width:100%;height:360px;"></div>

      </v-col>
    </v-row>
  </div>



  <div id="dmap">
    <h1>{{msg}}</h1>
    <div id="map" ref="map" style="width:90%; margin-left : 5%; height:500px;"></div>
  </div>

  <div style="width:100%; height:150px; overflow:auto">
<table class="table table-hover" width="100%" display="block" overflow= "auto">
	<thead>
		<tr>
			<th>번 호</th>
			<th>음식점 이름</th>
			<th>지번</th>
			<th>지역코드</th>
			<th>메뉴</th>
			<th>이용시간</th>
		</tr>

    <tr v-for="item in list" v-bind:key="item.no" >
        <td>{{item.idrestaurants}} </td>
        <td@click="view(item.no)" style="color:gray;">{{item.name}} </td>
        <td>{{item.jibun}} </td>
        <td>{{item.code}} </td>
        <td>{{item.menu}} </td>
        <td>{{item.time}} </td>
        <td>{{item.grade}} </td>
     </tr>
	</thead>
	<tbody>
	</tbody>
</table>
</div>


</div>
</template>


<script>
import http from '../../util/http-common';
import AppApi from '@/api/AppApi.js';

export default {
    data() {
      return {
        msg:"",
        temp : '',
        list : {},
        sido: {},
        sidovalue : '',
        gugun: {},
        gugunvalue : '',
        dong : {},
        dongvalue:'',
        comment: "",
        url: "",
        lat: 37.512,
        lon: 127.031,
        message:"",
      }
    },

    mounted() { 
        window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
        this.initsido();
    }, 

    methods : {
      onclick() {
      // this.getGeolocation()
      AppApi.getGeolocation(
        position => {
            console.log("position.coords", position.coords)
            this.lat = position.coords.latitude
            this.lon = position.coords.longitude
          },
        err => {
          console.log("err", err)
          if (err.code === 1) {
            console.log("사용자가 위치 정보 제공에 거부하였습니다.")
          } else {            
            console.log('기타 에러입니다')
            }
        }
      )
    },
     initMap() { 
        var container = document.getElementById('map'); 
        var options = {
              center: new kakao.maps.LatLng(this.lat, this.lon), level: 3 
        }; 
        var map = new kakao.maps.Map(container, options);
         //마커추가하려면 객체를 아래와 같이 하나 만든다
        var marker = new kakao.maps.Marker({ position: map.getCenter() });
        marker.setMap(map); 
    }, 
    view(no){
              this.$router.push("../store/store/" + no);
    },
    clearMap() {

    var tempstr = this.list[0].name;
    var container = document.getElementById('map'),
        mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 5 // 지도의 확대 레벨
    };  
    var map = new kakao
        .maps
        .Map(container, mapOption);

    var geocoder = new kakao
        .maps
        .services
        .Geocoder();




    //마커추가하려면 객체를 아래와 같이 하나 만든다
    for (var i = 0; i < this.list.length; i++) {
        geocoder.addressSearch(this.list[i].jibun, function (result, status) {
            if (status === kakao.maps.services.Status.OK) {
                var coords = new kakao
                    .maps
                    .LatLng(result[0].y, result[0].x);

                // 결과값으로 받은 위치를 마커로 표시합니다
                var marker = new kakao
                    .maps
                    .Marker({map: map, position: coords});

                // 인포윈도우로 장소에 대한 설명을 표시합니다
                var iwContent = '<div style="padding:2px;">' +this.list[i].name+ '<br><a href="https://map.kakao.com/link/to/Hello World!,' +
                    result[0].y + ',' + result[0].x + '" style="color:blue" target="_blank">길찾기</a></div>'
                var infowindow = new kakao.maps.InfoWindow({ content : iwContent });
                infowindow.open(map, marker);

            }

        })

        geocoder.addressSearch(this.list[0].jibun, function (result, status) {
        if (status === kakao.maps.services.Status.OK) {

            var coords = new kakao
                .maps
                .LatLng(result[0].y, result[0].x);

            // 결과값으로 받은 위치를 마커로 표시합니다
            var marker = new kakao
                .maps
                .Marker({map: map, position: coords});

            // 인포윈도우로 장소에 대한 설명을 표시합니다
            var infowindow = new kakao
                .maps
                .InfoWindow({
                    content: '<div style="padding:2px;">' + tempstr + '<br><a href="https://map.kakao.com/link/to/Hello World!,' +
                    result[0].y + ',' + result[0].x + '" style="color:blue" target="_blank">길찾기</a></div>'
                });
            infowindow.open(map, marker);

            // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
            map.setCenter(coords);
        }

    })
        /*
           var marker = new kakao.maps.Marker({ position:  new kakao.maps.LatLng(this.list[i].lat, this.list[i].lng) });
           marker.setMap(map);
           var iwContent ='<div style="padding:5px;">' + this.list[i].aptName+  '<br><a href="https://map.kakao.com/link/to/Hello World!,'+this.list[i].lat+','+this.list[i].lng+'" style="color:blue" target="_blank">길찾기</a></div>'
          
           infowindow.open(map, marker);
           */

    }

},
    addScript() { 
    const script = document.createElement('script');
     /* global kakao */ 
    script.onload = () => kakao.maps.load(this.initMap); 
    script.src = 'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=09e1abd5c5f6238940e4138d128c1b3f&libraries=services';   
     document.head.appendChild(script);
    },
    initsido() {
      http.post('/map/sido', {
      })
     .then(({ data }) => {
      let msg = '시도 불러오기에 실패하였습니다.';
      if (data != null) {
      this.sido = data;
    }else{
       alert(msg);
       }
     });
    },
    initgugun(sidovalue) {
      for( var i =0; i<this.sido.length; i++ ){
        if(sidovalue == this.sido[i].sido_name){
          this.temp = this.sido[i].sido_code;
        }
      }
      http.post('/map/gugun', {

        sido_name : sidovalue,
        sido_code : this.temp,
      })
     .then(({ data }) => {
      let msg = '구군 불러오기에 실패하였습니다.';
      if (data != null) {
      this.gugun = data;

    }else{
       alert(msg);
       }
     });
    },

    initdong(gugunvalue) {
       for( var i =0; i<this.gugun.length; i++ ){
        if(gugunvalue == this.gugun[i].gugun_name){
          this.temp = this.gugun[i].gugun_code;
        }
      }
      http.post('/map/dong', {
        gugun_name : gugunvalue,
        gugun_code : this.temp,
      })
     .then(({ data }) => {
      let msg = '동 불러오기에 실패하였습니다.';
      if (data != null) {
      this.dong = data;
    
    }else{
       alert(msg);
       }
     });
    },


    initlist(dongvalue) {
 
      http.post('/map/list', {
        dong : dongvalue,
      })
     .then(({ data }) => {
      let msg = '동 불러오기에 실패하였습니다.';
      if (data != null) {
      this.list = data;
      console.log(this.list);
      window.kakao && window.kakao.maps ? this.clearMap() : this.addScript();
      this.clearMap();
    }else{
       alert(msg);
       }
     });
    },
    search(){
        http.post('/map/list', {
        dong : this.message,
      })
     .then(({ data }) => {
      let msg = '동 불러오기에 실패하였습니다.';
      if (data != null) {
      this.list = data;
      console.log(this.list);
      window.kakao && window.kakao.maps ? this.clearMap() : this.addScript();
      this.clearMap();
    }else{
       alert(msg);
       }
     });
    },
    }
}

</script>

<style scoped>


</style>
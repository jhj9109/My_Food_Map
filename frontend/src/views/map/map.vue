<template>
<div class="container">
    <!-- 기존 주소 설정 삭제 / 검색 창 추가 -->
      <v-text-field
        v-model="message"        
        v-on:keyup.enter="search"
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

      <br>
<div>
    <v-row align="center">
      <v-col class="text-center" >
          <v-btn text large color="primary" @click="onclick">내 위치 기반 맛집</v-btn>
        <!-- 모바일 페이지 기준 맵 사이즈 조정, 라운드 처리 -->
        <div id="map" class="rounded-lg" style="width:100%;height:360px;"></div>

      </v-col>
    </v-row>
  </div>



  <div id="dmap">
    <h1>{{msg}}</h1>
    <div id="map" ref="map" style="width:90%; margin-left : 5%; height:150px;"></div>
  </div>

 

<!-- 음식점 뿌려 -->
  <RestaurantCard
      v-for="restaurant in restaurants"
      :key="restaurant.idrestaurants"
      :restaurantInfo="restaurant"
    />

</div>
</template>


<script>
import RestaurantCard from '@/components/restaurant/RestaurantCard';
import RestaurantApi from '@/api/RestaurantApi.js'
import http from '../../util/http-common';
import AppApi from '@/api/AppApi.js';

export default {
    components: {
        RestaurantCard
    },
    props: ['isScrollEnd'],
    data() {
        return {
            msg: "",
            temp: '',
            sido: {},
            sidovalue: '',
            gugun: {},
            gugunvalue: '',
            dong: {},
            dongvalue: '',
            comment: "",
            url: "",
            lat: 37.512,
            lon: 127.031,
            message: "",
            restaurants: [],
            allRestaurants: {},
            listData:[],
            listDatao:[],
            loading: true,
            offset: 0
        }
    },

    mounted() {
        window.kakao && window.kakao.maps
            ? this.initMap()
            : this.addScript();
    },

    methods: {

        fetchRestaurants() {
            const start = this.offset * 5
            const end = start + 4
            console.log("레스토랑 데이터 갱신 요청", this.allRestaurants.slice(start, end))
            const newArray = this
                .allRestaurants
                .slice(start, end)
            // console.log(`fetchRestaurants 대상은 ${start}~${end}, 5개 슬라이싱`, newArray)
            this.restaurants = [
                ...this.restaurants,
                ...newArray
            ]
            this.offset += 1
            this.loading = false
        },
        
        onclick() {
            // this.getGeolocation()
            AppApi.getGeolocation(position => {
                console.log("position.coords", position.coords)
                this.lat = position.coords.latitude
                this.lon = position.coords.longitude
            }, err => {
                console.log("err", err)
                if (err.code === 1) {
                    console.log("사용자가 위치 정보 제공에 거부하였습니다.")
                } else {
                    console.log('기타 에러입니다')
                }
            })
            http
                .post('/map/search', {
                    lat: this.lat,
                    lon: this.lon
                })
                .then(({data}) => {
                    let msg = '위치 불러오기에 실패하였습니다.';
                    if (data != null) {
                        this.allRestaurants = data;
                         for(var i=0; i<this.allRestaurants.length; i++ ){
                            this.listData[i] = this.allRestaurants[i].jibun;
                            this.listDatao[i] = this.allRestaurants[i].name;
                        }
                        console.log(this.listData);
                        window.kakao && window.kakao.maps
                            ? this.clearMap()
                            : this.addScript();
                        this.clearMap();
                        this.fetchRestaurants();
                    } else {
                        alert(msg);
                    }
                });
        },

        initMap() {
              AppApi.getGeolocation(position => {
                console.log("position.coords", position.coords)
                this.lat = position.coords.latitude
                this.lon = position.coords.longitude
            }, err => {
                console.log("err", err)
                if (err.code === 1) {
                    console.log("사용자가 위치 정보 제공에 거부하였습니다.")
                } else {
                    console.log('기타 에러입니다')
                }
            })
            http
                .post('/map/search', {
                    lat: this.lat,
                    lon: this.lon
                })
                .then(({data}) => {
                    let msg = '위치 불러오기에 실패하였습니다.';
                    if (data != null) {
                        this.allRestaurants = data;
                         for(var i=0; i<this.allRestaurants.length; i++ ){
                            this.listData[i] = this.allRestaurants[i].jibun;
                            this.listDatao[i] = this.allRestaurants[i].name;
                        }
                        console.log(this.listData);
                        window.kakao && window.kakao.maps
                            ? this.clearMap()
                            : this.addScript();
                        this.fetchRestaurants();
                    } else {
                        alert(msg);
                    }
                });
        },
        view(no) {
            this
                .$router
                .push("../store/store/" + no);
        },

    clearMap() {

    var mapContainer = document.getElementById('map');
    var mapOption = {
        center: new daum
            .maps
            .LatLng(37.450701, 126.570667),
        level: 5
    };

    var map = new daum
        .maps
        .Map(mapContainer, mapOption);
    var listDataa = this.listData;
    var listDataaa = this.listDatao;
    var geocoder = new daum
        .maps
        .services
        .Geocoder();
    listDataa.forEach(function (addr, index) {
        geocoder.addressSearch(addr, function (result, status) {
            if (status === daum.maps.services.Status.OK) {
                var coords = new daum
                    .maps
                    .LatLng(result[0].y, result[0].x);

                var marker = new daum
                    .maps
                    .Marker({map: map, position: coords});
                var infowindow = new daum
                    .maps
                    .InfoWindow({
                        content: '<div style="width:150px;text-align:center;padding:6px 0;">' +
                                listDataaa[index] + '<br>' + listDataa[index] + '<br><a href="https://map.kakao' +
                                '.com/link/to/' + listDataa[index] + ',' + result[0].y + ',' + result[0].x + '"' +
                                ' style="color:blue" target="_blank">길찾기</a></div>'
                    });
                (function (marker, infowindow) {
                    // 마커에 mouseover 이벤트를 등록하고 마우스 오버 시 인포윈도우를 표시합니다
                    kakao
                        .maps
                        .event
                        .addListener(marker, 'mouseover', function () {
                            infowindow.open(map, marker);
                        });

                    // 마커에 mouseout 이벤트를 등록하고 마우스 아웃 시 인포윈도우를 닫습니다
                    kakao
                        .maps
                        .event
                        .addListener(marker, 'mouseout', function () {
                            infowindow.close();
                        });
                })(marker, infowindow);
                if (index === 0) {
                    map.setCenter(coords);
                    console.log(coords);
                }
            }
        });
    });


        },
        addScript() {
            const script = document.createElement('script');
            /* global kakao */
            script.onload = () => kakao
                .maps
                .load(this.initMap);
            script.src = 'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=09e1abd5c5f6238940e' +
                    '4138d128c1b3f&libraries=services';
            document
                .head
                .appendChild(script);
        },

        // initsido() {
        //     http
        //         .post('/map/sido', {})
        //         .then(({data}) => {
        //             let msg = '시도 불러오기에 실패하였습니다.';
        //             if (data != null) {
        //                 this.sido = data;
        //             } else {
        //                 alert(msg);
        //             }
        //         });
        // },
        // initgugun(sidovalue) {
        //     for (var i = 0; i < this.sido.length; i++) {
        //         if (sidovalue == this.sido[i].sido_name) {
        //             this.temp = this
        //                 .sido[i]
        //                 .sido_code;
        //         }
        //     }
        //     http
        //         .post('/map/gugun', {

        //             sido_name: sidovalue,
        //             sido_code: this.temp
        //         })
        //         .then(({data}) => {
        //             let msg = '구군 불러오기에 실패하였습니다.';
        //             if (data != null) {
        //                 this.gugun = data;

        //             } else {
        //                 alert(msg);
        //             }
        //         });
        // },

        initdong(gugunvalue) {
            for (var i = 0; i < this.gugun.length; i++) {
                if (gugunvalue == this.gugun[i].gugun_name) {
                    this.temp = this
                        .gugun[i]
                        .gugun_code;
                }
            }
            http
                .post('/map/dong', {
                    gugun_name: gugunvalue,
                    gugun_code: this.temp
                })
                .then(({data}) => {
                    let msg = '동 불러오기에 실패하였습니다.';
                    if (data != null) {
                        this.dong = data;

                    } else {
                        alert(msg);
                    }
                });
        },

        initlist(dongvalue) {

            http
                .post('/map/list', {dong: dongvalue})
                .then(({data}) => {
                    let msg = '동 불러오기에 실패하였습니다.';
                    if (data != null) {
                        this.allRestaurants = data;
                        console.log(this.allRestaurants);
                        window.kakao && window.kakao.maps
                            ? this.clearMap()
                            : this.addScript();
                        this.clearMap();
                        this.fetchRestaurants();
                    } else {
                        alert(msg);
                    }
                });
        },
        search() {
            http
                .post('/map/list', {dong: this.message})
                .then(({data}) => {
                    let msg = '동 불러오기에 실패하였습니다.';
                    if (data != null) {
                        this.allRestaurants = data;
                        console.log(this.message)
                    
                        for(var i=0; i<this.allRestaurants.length; i++ ){
                           this.listData[i] = this.allRestaurants[i].jibun;
                            this.listDatao[i] = this.allRestaurants[i].name;
                        }
                        console.log(this.listData);
                        window.kakao && window.kakao.maps
                            ? this.clearMap()
                            : this.addScript();
                        this.clearMap();
                        this.fetchRestaurants();
                    } else {
                        alert(msg);
                    }
                });
        }
    },
    watch: {
        isScrollEnd: function (val) {
            // console.log("스크롤엔드 감지 :", val, this.loading)
            if (val && !this.loading) {
                this.loading = true
                // console.log("데이터 로딩 중", this.loading)
                this.fetchRestaurants()
            } else {
                // console.log("지나간다")
            }
        }
    }
}

</script>

<style scoped="scoped"></style>
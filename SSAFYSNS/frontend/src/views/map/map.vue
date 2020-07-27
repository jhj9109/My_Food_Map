<template>
<div class="container">
   <h1 id="boardtitle">
           Map
    </h1>
    <hr style="margin-bottom: 40px;">
    <div>
        시도 : <select>
	    <option></option>
        </select>
        구군 : <select>
	<option></option>
    </select>
    읍면동 :
     <select>
		<option ></option>
    </select>
  
    </div>
      <br>


  <div id="dmap">
    <h1>{{msg}}</h1>
    <div id="map" ref="map" style="width:90%; margin-left : 5%; height:500px;"></div>
  </div>

</div>
</template>


<script>
import http from '../../util/http-common';
export default {
    data() {
      return {
        msg:"",
        temp : '',
        list : {},
      }
    },

    mounted() { 
        window.kakao && window.kakao.maps ? this.initMap() : this.addScript();
    }, 

    methods : {
     initMap() { 
        var container = document.getElementById('map'); 
        var options = {
              center: new kakao.maps.LatLng(33.450701, 126.570667), level: 3 
        }; 
        var map = new kakao.maps.Map(container, options);
         //마커추가하려면 객체를 아래와 같이 하나 만든다
        var marker = new kakao.maps.Marker({ position: map.getCenter() });
        marker.setMap(map); 
    }, 
    view(no){
              this.$router.push("../apt/viewApt/" + no);
    },
    clearMap(){
        var container = document.getElementById('map'); 
        var options = {
              center: new kakao.maps.LatLng(this.list[0].lat, this.list[0].lng), level: 3 
        }; 
        var map = new kakao.maps.Map(container, options);
         //마커추가하려면 객체를 아래와 같이 하나 만든다
         for(var i=0; i<this.list.length; i++){
           var marker = new kakao.maps.Marker({ position:  new kakao.maps.LatLng(this.list[i].lat, this.list[i].lng) });
           marker.setMap(map); 
           var iwContent ='<div style="padding:5px;">' + this.list[i].aptName+  '<br><a href="https://map.kakao.com/link/to/Hello World!,'+this.list[i].lat+','+this.list[i].lng+'" style="color:blue" target="_blank">길찾기</a></div>'
           var infowindow = new kakao.maps.InfoWindow({ content : iwContent });
           infowindow.open(map, marker);

         }
        
    },
    addScript() { 
    const script = document.createElement('script');
     /* global kakao */ 
    script.onload = () => kakao.maps.load(this.initMap); 
    script.src = 'http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=763e8ace060e3472006ce87731702cbc&libraries=services';   
     document.head.appendChild(script);
    },

    
    }
}

</script>

<style scoped>


</style>
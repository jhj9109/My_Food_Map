<template>
    <v-card>
        <v-row>
            <v-col
              cols="12"
              sm="8"
              md="4"
            >
                <v-card-title>
                    <h1 class="display-1">리뷰 작성</h1>
                </v-card-title>
                <v-card-text>
                    <v-form>
                        <v-text-field
                            label="음식점"
                            :autofocus="true"
                            v-model="review.place"
                        />
                        <v-textarea 
                            label="Content"
                            :outlined="true"
                            v-model="review.content"
                        />
                        <v-file-input
                            :rules="rules"
                            prepend-icon="mdi-camera"
                            accept="image/png, image/jpeg, image/bmp"
                            label="이미지 첨부하기"
                            v-model="review.image"
                        >
                        </v-file-input>
                    </v-form>

                </v-card-text>
                <v-card-actions class="ml-5 mr-5">
                    <v-select
                        width="50"
                        :loader-height="1"
                        :items="[1,2,3,4,5]"
                        label="평점"
                        v-model="review.rank"
                    ></v-select>
                    <v-spacer></v-spacer>
                    <v-btn
                        @click="onCreate()"
                    >
                        Create
                    </v-btn>
                </v-card-actions>
            </v-col>
        </v-row>
    </v-card>
</template>

<script>
// import http from '../../util/http-common';
export default {
    data() {
        return {
            review: {
                place: "",
                content: "",
                rank: null,
                image: '',
            },
            rules: [
                value => !value || value.size < 2000000 || 'Img size should be less than 2 MB!',
            ],
        }
    },
    methods: {
        onCreate() {
            console.log("onCraete",this.review)
            // this.requestCreate(this.review)
            // this.$router.push('/review/리뷰아티클주소');
        },
        // onCreate2(){
        //     http.post('/review/create', {
        //             review: this.review
        //         })
        //         .then(res => {
        //             console.log("onCraete2",res)
        //             //아티클로 이동
        //             //this.$router.push('/review/리뷰아티클주소');
        //             //this.$router.go('/review/리뷰아티클주소');
        //         })
        //         .catch(err => {
        //             console.log("onCraete2",err)
        //             //에러페이지로 이동?
        //         })
        // },

    }
}
</script>

<style>

</style>
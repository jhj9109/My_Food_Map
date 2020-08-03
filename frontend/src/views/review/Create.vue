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
                        <v-img
                            v-if="review.image.url"
                            :src="review.image.url"
                            height="250px"
                            width="375px"
                        ></v-img>
                        <v-textarea 
                            label="Content"
                            :outlined="true"
                            v-model="review.content"
                        />
                        <v-text-field
                            label="이미지 첨부"
                            @click='pickFile'
                            v-model='review.image.name'
                            prepend-inner-icon='mdi-camera'>
                        </v-text-field>
                        <input
                            type="file"
                            style="display: none"
                            ref="image"
                            accept="image/*"
                            @change="onFilePicked"
                        >
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
import ReviewApi from "@/api/ReviewApi.js";

export default {
    data() {
        return {
            review: {
                place: "",
                content: "",
                rank: 0,
				image: {
					name: '',
					url: '',
					file: '',
				}
            },
            title: "Image Upload",
            dialog: false,
            rules: [
                value => !value || value.size < 2000000 || 'Img size should be less than 2 MB!',
            ],
        }
    },
    props: ['userInfo'],
    methods: {
        onCreate() {
            console.log("onCraete",this.review)
            const data = {
                userId: this.userInfo.userId,
                review: this.review
            }
            ReviewApi.requestCreate(
                data,
                res => {
                    console.log("resquestCreate 성공, res : ", res)
                    if(res.data.status === 'ok') {
                        // 리뷰 작성 성공 => 작성한 리뷰 페이지로 라우팅
                        alert("리뷰가 작성 되었습니다.")
                        this.$router.push( { name: 'Review', params: {reviewId: res.data.reviewId }} );
                    } else {
                        // 리뷰 작성 실패 => 에러 원인 알려주기?
                        console.log("리뷰작성 실패, res.data: ", res.data)
                        alert(res.data.message || "리뷰가 작성에 실패했습니다.")
                    }
                },
                err => {
                    console.log(err)
                    // 에러작성 페이지로
                    this.$router.push( { name: 'ErrorPage' })
                }
            )
        },
        pickFile() {
			console.log("픽파일")
			console.log(this)
			console.log(this.$refs)
			console.log(this.$refs.image) // ref에 등록된 이름 기준으로 찾아냄 => input
            this.$refs.image.click()
        },
        onFilePicked(e) {
            console.log("e입니다",e) // change 이벤트
            console.log("e.target입니다",e.target) // input
            console.log("e.target.files입니다",e.target.files) // Filelist
            console.log("e.target.files[0]입니다",e.target.files[0]) // Filelist
            console.log("URL.createObjectURL(e.target.files[0])입니다",URL.createObjectURL(e.target.files[0])) // Filelist
        
            const files = e.target.files
            if (files[0] !== undefined) {
                this.review.image.name = files[0].name
                if (this.review.image.name.lastIndexOf('.') <= 0) {
                    return
                }
                const fr = new FileReader()
                fr.readAsDataURL(files[0])
                fr.addEventListener('load', () => {
                    this.review.image.url = fr.result
                    this.review.image.file = files[0] // this is an image file that can be sent to server...
                })
            } else {
                this.review.image.name = ''
                this.review.image.url = ''
                this.review.image.file = ''
            }
        }

    }
}
</script>

<style>

</style>
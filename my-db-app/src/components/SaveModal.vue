<template>
  <!-- Button trigger modal -->
  <button
    type="button"
    class="btn btn-outline-success btn-sm my-3 mx-2"
    data-bs-toggle="modal"
    data-bs-target="#saveModal"
    @click="pressSaveButton()"
  >
    추가
  </button>
  <!-- Modal -->
  <div
    class="modal fade"
    id="saveModal"
    tabindex="-1"
    aria-labelledby="saveModal"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="saveModal">새로 추가하시겠습니까?</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body mb-3">
          <label for="exampleFormControlInput1" class="form-label">단어</label>
          <input
            type="text"
            class="form-control"
            v-model="this.saveWord.concept"
          />
          <label for="exampleFormControlInput1" class="form-label"
            >카테고리</label
          >
          <input
            type="text"
            class="form-control"
            v-model="this.saveWord.category"
          />

          <label for="exampleFormControlInput1" class="form-label">의미</label>
          <textarea
            type="text"
            class="form-control"
            v-model="this.saveWord.meaning"
            style="height: 15rem"
          />

          <label for="exampleFormControlInput1" class="form-label"
            >Full Name</label
          >
          <input
            type="text"
            class="form-control"
            v-model="this.saveWord.fullName"
          />
        </div>

        <div class="modal-footer">
          <button
            type="button"
            class="btn btn-secondary mx-1"
            data-bs-dismiss="modal"
          >
            닫기
          </button>
          <button
            type="button"
            class="btn btn-primary mx-1"
            data-bs-dismiss="modal"
            @click="pressSave()"
          >
            저장
          </button>
          <!-- <div>{{ this.saveWord }}</div> -->
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axios from 'axios'

export default {
  data() {
    return {
      saveWord: {}
    }
  },
  methods: {
    pressSaveButton() {
      this.saveWord.concept = ''
      this.saveWord.category = ''
      this.saveWord.meaning = ''
      this.saveWord.fullName = ''
    },
    pressSave() {
      console.log('save clicked' + JSON.stringify(this.saveWord))
      axios.defaults.headers.post['Content-Type'] = 'application/json'
      axios
        .post('http://localhost:8085/api/words', JSON.stringify(this.saveWord))
        .then((response) => {
          // 리스트 추가?
          console.log(JSON.stringify.this.saveWord)
        })
        .catch((error) => {
          console.error
        })
      this.$router.go()
    }
  }
}
</script>

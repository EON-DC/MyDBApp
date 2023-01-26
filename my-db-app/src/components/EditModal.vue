<template>
  <!-- Button trigger modal -->
  <button
    type="button"
    class="btn btn-outline-primary btn-sm m-2"
    style="height: 2rem"
    data-bs-toggle="modal"
    :data-bs-target="'#editModal' + word?.id"
    @click="pressEditButton()"
  >
    edit
  </button>
  <!-- Modal -->
  <div
    class="modal fade"
    :id="'editModal' + word?.id"
    tabindex="-1"
    aria-labelledby="'editModal' + word?.id"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="'editModal' + word?.id">
            수정하시겠습니까?
          </h5>
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
            v-model="this.wordDto.concept"
          />
          <label for="exampleFormControlInput1" class="form-label"
            >카테고리</label
          >
          <input
            type="text"
            class="form-control"
            v-model="this.wordDto.category"
          />

          <label for="exampleFormControlInput1" class="form-label">의미</label>
          <textarea
            type="text"
            class="form-control"
            v-model="this.wordDto.meaning"
            style="height: 15rem"
          />

          <label for="exampleFormControlInput1" class="form-label"
            >Full Name</label
          >
          <input
            type="text"
            class="form-control"
            v-model="this.wordDto.fullName"
          />
        </div>

        <div class="modal-footer">
          <DeleteButton :id="this.word.id" />

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
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import axios from 'axios'
import DeleteButton from './DeleteButton.vue'

export default {
  props: ['word'],
  data() {
    return {
      wordDto: {}
    }
  },
  methods: {
    pressEditButton() {
      this.wordDto = JSON.parse(JSON.stringify(this.word))
    },
    pressSave() {
      this.word.concept = JSON.parse(JSON.stringify(this.wordDto.concept))
      this.word.category = JSON.parse(JSON.stringify(this.wordDto.category))
      this.word.meaning = JSON.parse(JSON.stringify(this.wordDto.meaning))
      this.word.fullName = JSON.parse(JSON.stringify(this.wordDto.fullName))

      console.log('this : ' + JSON.stringify(this.word?.concept))
      axios
        .patch(
          'http://localhost:8085/api/words/' + this.wordDto.id,
          JSON.stringify(this.wordDto)
        )
        .then((response) => {
          console.log('WordDto was sended...')
        })
        .catch((error) => {
          console.error
        })
    }
  },
  components: { DeleteButton }
}
</script>

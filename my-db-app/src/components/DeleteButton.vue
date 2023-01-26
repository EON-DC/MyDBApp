<template>
  <button
    type="button"
    class="btn btn-outline-danger mx-1"
    @click="deleteWord()"
    data-bs-dismiss="modal"
  >
    삭제
  </button>
</template>
<script>
import axios from 'axios'
export default {
  props: ['id'],
  methods: {
    deleteWord() {
      if (confirm('정말로 삭제하시겠습니까?')) {
        console.log('sending delete ' + JSON.stringify(this.id))
        axios.delete(
          'http://localhost:8085/api/words/' + this.id,
          JSON.stringify(this.id)
        )
        this.$router.go()
      }
    }
  }
}
</script>

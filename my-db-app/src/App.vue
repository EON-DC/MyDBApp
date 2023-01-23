<template>
  <div>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <div class="container-fluid">
        <router-link to="/">
          <span>Home</span>
        </router-link>
        <router-link to="/study">
          <span>Study</span>
        </router-link>
      </div>
    </nav>

    <router-view @todayCountChange="todayCountChange" />

    <div class="mt-5 p-2 text-start">
      <hr />
      <p>
        오늘 푼 문제 개수 : {{ this.today_count }}
        <!-- <span v-show="today_count"
          >( 정답률 : {{ correction_count / today_count }} %)</span
        > -->
      </p>

      <p>현재 DB에 등록된 단어의 개수 : {{ this.db_status.rowCount }} 개</p>

      <br />
    </div>
  </div>
</template>
<script>
import axios from 'axios'

export default {
  name: '',
  components: {},
  data() {
    return {
      words: [],
      quiz: '',
      db_status: {},
      today_count: 0,
      correction_count: 0
    }
  },
  methods: {
    getStatus() {
      axios
        .get('http://localhost:8085/api/words/status')
        .then((response) => {
          this.db_status = response.data
          console.log('getStatus called')
        })
        .catch((error) => {
          console.error
        })
    },
    todayCountChange(e) {
      console.log('#app. count ' + e)
      this.today_count = e
    }
  },

  mounted() {
    console.log('app called')
    this.getStatus()
  },
  computed: {}
}
</script>

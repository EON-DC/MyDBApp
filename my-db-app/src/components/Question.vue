<template>
  <span>
    <img
      src="../assets/question.svg"
      alt=""
      data-bs-toggle="collapse"
      :href="'#collapse' + selected.id"
      data-placement="bottom"
      role="button"
      aria-expanded="false"
      :aria-controls="'collapse' + selected.id"
    />
  </span>
  <div>
    <div class="collapse show m-2" :id="'collapse' + selected.id">
      <div class="card card-body">
        <div class="row">
          <div class="col-10">
            <span class="h6"> {{ selected.concept }}</span>

            <span v-if="selected.fullName != 'null'">
              &nbsp;&nbsp;&nbsp;{{ selected.fullName }}
            </span>
            <br />
            카테고리 : {{ selected.category }}
          </div>
          <div class="col-2">
            <EditModal :word="selected" />
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import EditModal from '../components/EditModal.vue'
export default {
  props: ['selected', 'picked'], // concept, answerIndex, select
  components: { EditModal },
  data() {
    return {
      isSelected: false
    }
  },
  methods: {},
  computed: {}
}
</script>

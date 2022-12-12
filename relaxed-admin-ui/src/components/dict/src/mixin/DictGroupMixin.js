import DictMixin from "@/components/dict/src/mixin/DictMixin";
export default {
  name: 'DictGroupMixin',
  mixins: [DictMixin],
  props: {
    // eslint-disable-next-line vue/require-default-prop
    value: {
      type: [String, Number, Boolean, Array]
    },
    disabled: {
      type: Boolean,
      default: false
    }
  },
  data () {
    return {
      selectedValue: this.value
    }
  },
  watch: {
    value () {
      this.selectedValue = this.value
    }
  },
  methods: {
    handleChange (val) {
      console.log("当前改变值",val)
      if (val && val.target) {
        this.selectedValue = val.target.value
      } else {
        this.selectedValue = val
      }
      // v-decorator 方式的表单值联动
      this.$emit('change', this.selectedValue)
      // v-model 方式的表单值联动
      this.$emit('input', this.selectedValue)
    }
  }
}

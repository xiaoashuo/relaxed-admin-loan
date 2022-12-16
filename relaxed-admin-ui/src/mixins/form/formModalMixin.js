import formMixin from "@/mixins/form/formMixin";
export default {
  mixins:[formMixin],
  data(){
    return{
      title:'',
      visible: false
    }
  },
  methods:{
    show(payload){
      this.title=payload.title
      this.visible=true
    },
    add(payload){
      this.buildCreateForm(payload)
      this.show(payload)
    },
    update(payload){
      this.buildUpdatedForm(payload)
      this.show(payload)
    },
    close(){
      this.$emit('beforeClose')
      this.beforeClose()
      this.visible=false
      this.afterClose()

    },
    beforeClose(){

    },
    afterClose(){

    }

  }
}

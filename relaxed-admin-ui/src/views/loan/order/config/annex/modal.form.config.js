import {FILE_UPLOAD_URL} from '@/constants/SysConstants'
export const modalFormConfig= {
  colLayout:{
    span: 24
  },
  formItems:[



         {
      type:'dict-select',
      label: '文件类型',
      field:'fileType',
      placeholder:'请输入文件类型',
      rules:[
        { required: true, message: '请输入文件类型', trigger: 'blur' },
      ],
           config:{
        dictCode:'file_type',
             clearable:true
           }
      },


         {
      type:'upload',
      label: '上传文件',
      field:'uploadData',
      rules:[
        { required: true, message: '请选择文件', trigger: 'blur' },
      ],
           config: {

             limit:1,
             drag:true,
             uploadUrl:FILE_UPLOAD_URL,
             fileType:['txt','docx','xlsx'],
             data:{
               relatedPath:"profile/annex",
               splitDate:false
             },
           }
      },


  ],
}

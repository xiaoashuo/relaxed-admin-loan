const enumFiles=require.context('./enums',true,/\.js$/)
const keys=enumFiles.keys()
let allEnums={}
for (let key of keys){
  const currentEnum=require('./enums'+key.split('.')[1])
  const enumObj=currentEnum.default
  for (const item in enumObj){
    allEnums[item]=enumObj[item]
  }
}
export default allEnums

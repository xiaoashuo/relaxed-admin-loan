
import MenuEnum from "@/constants/enums/MenuEnum";
import LeftNavSteps from "./LeftNavSteps";
import LeftPlusNavSteps from "./LeftPlusNavSteps"
import TopNavSteps from './TopNavSteps'
import TopLeftNavSteps from './TopLeftNavSteps'
const json={}
json[MenuEnum.MENU_LAYOUT.LEFT_NAV.value]=LeftNavSteps
json[MenuEnum.MENU_LAYOUT.LEFT_PLUS_NAV.value]=LeftPlusNavSteps
json[MenuEnum.MENU_LAYOUT.TOP_NAV.value]=TopNavSteps
json[MenuEnum.MENU_LAYOUT.TOP_LEFT_NAV.value]=TopLeftNavSteps
export default json

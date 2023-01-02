const DATE_TIME_FORMAT="YYYY-MM-DD HH:mm:ss"
import dayjs from "dayjs";
import utc from 'dayjs/plugin/utc'
dayjs.extend(utc)
export function formatUtcString(utcString,format=DATE_TIME_FORMAT) {

  if (!utcString){
    return null
  }
  return dayjs.utc(utcString).utcOffset(8).format(format)
}

class LocalStorage {
  setCache(key, value) {
    window.localStorage.setItem(key, JSON.stringify(value))
  }

  getCache(key) {
    let value = window.localStorage.getItem(key)
    if (value) {
      return JSON.parse(value)
    }
  }

  deleteCache(key) {
    window.localStorage.removeItem(key)
  }

  clearAll() {
    window.localStorage.clear()
  }
}
class SessionStorage {
  setCache(key, value) {
    window.sessionStorage.setItem(key, JSON.stringify(value))
  }

  getCache(key) {
    let value = window.sessionStorage.getItem(key)
    if (value) {
      return JSON.parse(value)
    }
  }

  deleteCache(key) {
    window.sessionStorage.removeItem(key)
  }

  clearAll() {
    window.sessionStorage.clear()
  }
}
export  default {
  local: new LocalStorage(),
  session: new SessionStorage()
}

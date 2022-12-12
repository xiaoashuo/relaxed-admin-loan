class LocalCache {
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

  clearCache(key) {
    window.localStorage.clear()
  }
}

export default new LocalCache()

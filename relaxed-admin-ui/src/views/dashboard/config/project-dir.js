export const projectDir = `
.
|-- LICENSE
|-- README-zh.md
|-- README.md
|-- babel.config.js
|-- build
|   \`-- index.js
|-- jest.config.js
|-- jsconfig.json
|-- mock
|   |-- index.js
|   |-- mock-server.js
|   |-- table.js
|   |-- user.js
|   \`-- utils.js
|-- package-lock.json
|-- package.json
|-- postcss.config.js
|-- public
|   |-- favicon.ico
|   \`-- index.html
|-- src
|   |-- App.vue
|   |-- api
|   |   |-- auth.js
|   |   |-- dict.js
|   |   |-- permission.js
|   |   |-- role.js
|   |   |-- select.js
|   |   |-- system.js
|   |   \`-- user.js
|   |-- assets
|   |   |-- 404_images
|   |   |   |-- 404.png
|   |   |   \`-- 404_cloud.png
|   |   \`-- img
|   |       \`-- logo.svg
|   |-- components
|   |   |-- Breadcrumb
|   |   |   \`-- index.vue
|   |   |-- ErrorLog
|   |   |   \`-- index.vue
|   |   |-- Hamburger
|   |   |   \`-- index.vue
|   |   |-- HeaderSearch
|   |   |   \`-- index.vue
|   |   |-- PanThumb
|   |   |   \`-- index.vue
|   |   |-- RightPanel
|   |   |   \`-- index.vue
|   |   |-- Screenfull
|   |   |   \`-- index.vue
|   |   |-- SvgIcon
|   |   |   \`-- index.vue
|   |   |-- TextLink
|   |   |   |-- index.js
|   |   |   \`-- src
|   |   |       \`-- TextLink.vue
|   |   |-- ThemePicker
|   |   |   \`-- index.vue
|   |   |-- TreeSelect
|   |   |   |-- index.js
|   |   |   \`-- src
|   |   |       \`-- tree-select.vue
|   |   |-- card
|   |   |   |-- index.js
|   |   |   \`-- src
|   |   |       \`-- card.vue
|   |   |-- code
|   |   |   |-- index.js
|   |   |   \`-- src
|   |   |       \`-- code.vue
|   |   |-- desctiptions
|   |   |   |-- index.js
|   |   |   \`-- src
|   |   |       \`-- descriptions.vue
|   |   |-- dict
|   |   |   |-- index.js
|   |   |   \`-- src
|   |   |       \`-- DictSelect.vue
|   |   |-- form
|   |   |   |-- index.js
|   |   |   \`-- src
|   |   |       \`-- form.vue
|   |   |-- icons
|   |   |   |-- element-icons.js
|   |   |   |-- index.vue
|   |   |   \`-- svg-icons.js
|   |   |-- image
|   |   |   |-- index.js
|   |   |   \`-- src
|   |   |       \`-- image.vue
|   |   |-- pdf
|   |   |   |-- pdf.vue
|   |   |   \`-- src
|   |   |-- select
|   |   |   |-- index.js
|   |   |   \`-- src
|   |   |       \`-- select.vue
|   |   |-- table
|   |   |   |-- index.js
|   |   |   \`-- src
|   |   |       \`-- table.vue
|   |   \`-- tree
|   |       |-- index.js
|   |       \`-- src
|   |           \`-- tree.vue
|   |-- global
|   |   |-- directive
|   |   |   \`-- permission.js
|   |   |-- filters
|   |   |   \`-- index.js
|   |   |-- index.js
|   |   |-- registerDirective.js
|   |   \`-- registerFilter.js
|   |-- icons
|   |   |-- index.js
|   |   |-- svg
|   |   |   |-- bug.svg
|   |   |   |-- dashboard.svg
|   |   |   |-- document.svg
|   |   |   |-- example.svg
|   |   |   |-- exit-fullscreen.svg
|   |   |   |-- eye-open.svg
|   |   |   |-- eye.svg
|   |   |   |-- form.svg
|   |   |   |-- fullscreen.svg
|   |   |   |-- link.svg
|   |   |   |-- log.svg
|   |   |   |-- nested.svg
|   |   |   |-- password.svg
|   |   |   |-- search.svg
|   |   |   |-- table.svg
|   |   |   |-- tree.svg
|   |   |   \`-- user.svg
|   |   \`-- svgo.yml
|   |-- layout
|   |   |-- components
|   |   |   |-- AppMain.vue
|   |   |   |-- Navbar.vue
|   |   |   |-- Settings
|   |   |   |   \`-- index.vue
|   |   |   |-- Sidebar
|   |   |   |   |-- FixiOSBug.js
|   |   |   |   |-- Item.vue
|   |   |   |   |-- Link.vue
|   |   |   |   |-- Logo.vue
|   |   |   |   |-- SidebarItem.vue
|   |   |   |   \`-- index.vue
|   |   |   |-- TagsView
|   |   |   |   |-- ScrollPane.vue
|   |   |   |   \`-- index.vue
|   |   |   |-- index.js
|   |   |   |-- page-content
|   |   |   |   |-- index.js
|   |   |   |   \`-- src
|   |   |   |       \`-- page-content.vue
|   |   |   |-- page-lov
|   |   |   |   |-- index.js
|   |   |   |   \`-- src
|   |   |   |       \`-- page-lov.vue
|   |   |   |-- page-modal
|   |   |   |   |-- index.js
|   |   |   |   \`-- src
|   |   |   |       \`-- page-modal.vue
|   |   |   \`-- page-search
|   |   |       |-- index.js
|   |   |       \`-- src
|   |   |           \`-- page-search.vue
|   |   |-- index.vue
|   |   \`-- mixin
|   |       |-- ResizeHandler.js
|   |       \`-- usePageSearch.js
|   |-- main.js
|   |-- permission.js
|   |-- router
|   |   |-- RouteMap.js
|   |   |-- dynamicRouter.js
|   |   \`-- index.js
|   |-- settings.js
|   |-- store
|   |   |-- getters.js
|   |   |-- index.js
|   |   \`-- modules
|   |       |-- app.js
|   |       |-- auth.js
|   |       |-- dict.js
|   |       |-- errorLog.js
|   |       |-- router.js
|   |       |-- select.js
|   |       |-- settings.js
|   |       |-- system.js
|   |       \`-- tagsView.js
|   |-- styles
|   |   |-- btn.scss
|   |   |-- element-ui.scss
|   |   |-- element-variables.scss
|   |   |-- index.scss
|   |   |-- mixin.scss
|   |   |-- sidebar.scss
|   |   |-- transition.scss
|   |   \`-- variables.scss
|   |-- utils
|   |   |-- Constant.js
|   |   |-- auth.js
|   |   |-- cache.js
|   |   |-- clipboard.js
|   |   |-- date-format.js
|   |   |-- error-log.js
|   |   |-- fileUtil.js
|   |   |-- get-page-title.js
|   |   |-- index.js
|   |   |-- open-window.js
|   |   |-- request
|   |   |   |-- config.js
|   |   |   |-- index.js
|   |   |   \`-- request.js
|   |   |-- scroll-to.js
|   |   |-- strUtil.js
|   |   |-- usePermission.js
|   |   \`-- validate.js
|   \`-- views
|       |-- 404.vue
|       |-- dashboard
|       |   |-- config
|       |   |   |-- dependencies.js
|       |   |   |-- dev-dependencies.js
|       |   |   |-- project-dir.js
|       |   |   \`-- technology-stacks.js
|       |   \`-- index.vue
|       |-- iframe
|       |   \`-- index.vue
|       |-- login
|       |   \`-- index.vue
|       |-- redirect
|       |   \`-- index.vue
|       \`-- system
|           |-- dict
|           |   |-- config
|           |   |   |-- content.config.js
|           |   |   |-- content.item.config.js
|           |   |   |-- modal.config.js
|           |   |   |-- modal.item.config.js
|           |   |   \`-- search.config.js
|           |   |-- dict-item.vue
|           |   \`-- dict.vue
|           |-- log
|           |   |-- access
|           |   |   |-- access-page.vue
|           |   |   \`-- config
|           |   |       |-- content.config.js
|           |   |       \`-- search.config.js
|           |   \`-- option
|           |       |-- config
|           |       |   |-- content.config.js
|           |       |   \`-- search.config.js
|           |       \`-- operate-page.vue
|           |-- menu
|           |   |-- config
|           |   |   |-- content.config.js
|           |   |   |-- lov.config.js
|           |   |   |-- modal.config.js
|           |   |   \`-- search.config.js
|           |   \`-- index.vue
|           |-- nested
|           |   |-- Nested.vue
|           |   \`-- NestedChildren.vue
|           |-- profile
|           |   |-- components
|           |   |   |-- Account.vue
|           |   |   |-- Activity.vue
|           |   |   |-- Timeline.vue
|           |   |   \`-- UserCard.vue
|           |   \`-- index.vue
|           |-- role
|           |   |-- config
|           |   |   |-- content.config.js
|           |   |   |-- modal.config.js
|           |   |   \`-- search.config.js
|           |   |-- detail.vue
|           |   \`-- index.vue
|           \`-- user
|               |-- config
|               |   |-- content.config.js
|               |   |-- modal.auth.config.js
|               |   |-- modal.config.js
|               |   \`-- search.config.js
|               \`-- index.vue
|-- tests
|   \`-- unit
|       |-- components
|       |   |-- Breadcrumb.spec.js
|       |   |-- Hamburger.spec.js
|       |   \`-- SvgIcon.spec.js
|       \`-- utils
|           |-- formatTime.spec.js
|           |-- param2Obj.spec.js
|           |-- parseTime.spec.js
|           \`-- validate.spec.js
|-- tree.txt
\`-- vue.config.js
`

webpackJsonp([25,53],{"83sx":function(t,o,e){o=t.exports=e("FZ+f")(!1),o.push([t.i,'.todoapp{font:14px Helvetica Neue,Helvetica,Arial,sans-serif;line-height:1.4em;color:#4d4d4d;min-width:230px;max-width:550px;margin:0 auto;font-weight:300;background:#fff;z-index:1;position:relative}.todoapp,.todoapp button{-webkit-font-smoothing:antialiased;-moz-osx-font-smoothing:grayscale}.todoapp button{margin:0;padding:0;border:0;background:none;font-size:100%;vertical-align:baseline;font-family:inherit;font-weight:inherit;color:inherit;-webkit-appearance:none;appearance:none}.todoapp :focus{outline:0}.todoapp .hidden{display:none}.todoapp .todoapp{background:#fff;margin:130px 0 40px;position:relative;box-shadow:0 2px 4px 0 rgba(0,0,0,.2),0 25px 50px 0 rgba(0,0,0,.1)}.todoapp .todoapp input::-webkit-input-placeholder{font-style:italic;font-weight:300;color:#e6e6e6}.todoapp .todoapp input::-moz-placeholder{font-style:italic;font-weight:300;color:#e6e6e6}.todoapp .todoapp input::input-placeholder{font-style:italic;font-weight:300;color:#e6e6e6}.todoapp .todoapp h1{position:absolute;top:-155px;width:100%;font-size:100px;font-weight:100;text-align:center;color:rgba(175,47,47,.15);-webkit-text-rendering:optimizeLegibility;-moz-text-rendering:optimizeLegibility;text-rendering:optimizeLegibility}.todoapp .edit,.todoapp .new-todo{position:relative;margin:0;width:100%;font-size:18px;font-family:inherit;font-weight:inherit;line-height:1.4em;border:0;color:inherit;padding:6px;border:1px solid #999;box-shadow:inset 0 -1px 5px 0 rgba(0,0,0,.2);box-sizing:border-box;-webkit-font-smoothing:antialiased;-moz-osx-font-smoothing:grayscale}.todoapp .new-todo{padding:10px 16px 16px 60px;border:none;background:rgba(0,0,0,.003);box-shadow:inset 0 -2px 1px rgba(0,0,0,.03)}.todoapp .main{position:relative;z-index:2;border-top:1px solid #e6e6e6}.todoapp .toggle-all{text-align:center;border:none;opacity:0;position:absolute}.todoapp .toggle-all+label{width:60px;height:34px;font-size:0;position:absolute;top:-52px;left:-13px;-webkit-transform:rotate(90deg);transform:rotate(90deg)}.todoapp .toggle-all+label:before{content:"\\276F";font-size:22px;color:#e6e6e6;padding:10px 27px}.todoapp .toggle-all:checked+label:before{color:#737373}.todoapp .todo-list{margin:0;padding:0;list-style:none}.todoapp .todo-list li{position:relative;font-size:24px;border-bottom:1px solid #ededed}.todoapp .todo-list li:last-child{border-bottom:none}.todoapp .todo-list li.editing{border-bottom:none;padding:0}.todoapp .todo-list li.editing .edit{display:block;width:506px;padding:12px 16px;margin:0 0 0 43px}.todoapp .todo-list li.editing .view{display:none}.todoapp .todo-list li .toggle{text-align:center;width:40px;height:auto;position:absolute;top:0;bottom:0;margin:auto 0;border:none;-webkit-appearance:none;appearance:none;opacity:0}.todoapp .todo-list li .toggle+label{background-image:url("data:image/svg+xml;utf8,%3Csvg%20xmlns%3D%22http%3A//www.w3.org/2000/svg%22%20width%3D%2240%22%20height%3D%2240%22%20viewBox%3D%22-10%20-18%20100%20135%22%3E%3Ccircle%20cx%3D%2250%22%20cy%3D%2250%22%20r%3D%2250%22%20fill%3D%22none%22%20stroke%3D%22%23ededed%22%20stroke-width%3D%223%22/%3E%3C/svg%3E");background-repeat:no-repeat;background-position:0;background-size:36px}.todoapp .todo-list li .toggle:checked+label{background-size:36px;background-image:url("data:image/svg+xml;utf8,%3Csvg%20xmlns%3D%22http%3A//www.w3.org/2000/svg%22%20width%3D%2240%22%20height%3D%2240%22%20viewBox%3D%22-10%20-18%20100%20135%22%3E%3Ccircle%20cx%3D%2250%22%20cy%3D%2250%22%20r%3D%2250%22%20fill%3D%22none%22%20stroke%3D%22%23bddad5%22%20stroke-width%3D%223%22/%3E%3Cpath%20fill%3D%22%235dc2af%22%20d%3D%22M72%2025L42%2071%2027%2056l-4%204%2020%2020%2034-52z%22/%3E%3C/svg%3E")}.todoapp .todo-list li label{word-break:break-all;padding:15px 15px 15px 50px;display:block;line-height:1;font-size:14px;transition:color .4s}.todoapp .todo-list li.completed label{color:#d9d9d9;text-decoration:line-through}.todoapp .todo-list li .destroy{display:none;position:absolute;top:0;right:10px;bottom:0;width:40px;height:40px;margin:auto 0;font-size:30px;color:#cc9a9a;transition:color .2s ease-out}.todoapp .todo-list li .destroy:hover{color:#af5b5e}.todoapp .todo-list li .destroy:after{content:"\\D7"}.todoapp .todo-list li:hover .destroy{display:block}.todoapp .todo-list li .edit{display:none}.todoapp .todo-list li.editing:last-child{margin-bottom:-1px}.todoapp .footer{color:#777;position:relative;padding:10px 15px;height:40px;text-align:center;border-top:1px solid #e6e6e6}.todoapp .footer:before{content:"";position:absolute;right:0;bottom:0;left:0;height:40px;overflow:hidden;box-shadow:0 1px 1px rgba(0,0,0,.2),0 8px 0 -3px #f6f6f6,0 9px 1px -3px rgba(0,0,0,.2),0 16px 0 -6px #f6f6f6,0 17px 2px -6px rgba(0,0,0,.2)}.todoapp .todo-count{float:left;text-align:left}.todoapp .todo-count strong{font-weight:300}.todoapp .filters{margin:0;padding:0;position:relative;z-index:1;list-style:none}.todoapp .filters li{display:inline}.todoapp .filters li a{color:inherit;font-size:12px;padding:3px 7px;text-decoration:none;border:1px solid transparent;border-radius:3px}.todoapp .filters li a:hover{border-color:rgba(175,47,47,.1)}.todoapp .filters li a.selected{border-color:rgba(175,47,47,.2)}.todoapp .clear-completed,.todoapp html .clear-completed:active{float:right;position:relative;line-height:20px;text-decoration:none;cursor:pointer}.todoapp .clear-completed:hover{text-decoration:underline}.todoapp .info{margin:65px auto 0;color:#bfbfbf;font-size:10px;text-shadow:0 1px 0 hsla(0,0%,100%,.5);text-align:center}.todoapp .info p{line-height:1}.todoapp .info a{color:inherit;text-decoration:none;font-weight:400}.todoapp .info a:hover{text-decoration:underline}@media screen and (-webkit-min-device-pixel-ratio:0){.todoapp .todo-list li .toggle,.todoapp .toggle-all{background:none}.todoapp .todo-list li .toggle{height:40px}}@media (max-width:430px){.todoapp .footer{height:50px}.todoapp .filters{bottom:10px}}',""])},DVEb:function(t,o,e){"use strict";function i(t){return t&&t.__esModule?t:{default:t}}Object.defineProperty(o,"__esModule",{value:!0});var n=e("mvHQ"),a=i(n),d=e("xjg9"),l=i(d),r={all:function(t){return t},active:function(t){return t.filter(function(t){return!t.done})},completed:function(t){return t.filter(function(t){return t.done})}},s=[{text:"star this repository",done:!1},{text:"fork this repository",done:!1},{text:"follow author",done:!1},{text:"vue-element-admin",done:!0},{text:"vue",done:!0},{text:"element-ui",done:!0},{text:"axios",done:!0},{text:"webpack",done:!0}];o.default={components:{Todo:l.default},data:function(){return{visibility:"all",filters:r,todos:s}},computed:{allChecked:function(){return this.todos.every(function(t){return t.done})},filteredTodos:function(){return r[this.visibility](this.todos)},remaining:function(){return this.todos.filter(function(t){return!t.done}).length}},methods:{setLocalStorgae:function(){window.localStorage.setItem("todos",(0,a.default)(this.todos))},addTodo:function(t){var o=t.target.value;o.trim()&&(this.todos.push({text:o,done:!1}),this.setLocalStorgae()),t.target.value=""},toggleTodo:function(t){t.done=!t.done,this.setLocalStorgae()},deleteTodo:function(t){this.todos.splice(this.todos.indexOf(t),1),this.setLocalStorgae()},editTodo:function(t){var o=t.todo,e=t.value;o.text=e,this.setLocalStorgae()},clearCompleted:function(){this.todos=this.todos.filter(function(t){return!t.done}),this.setLocalStorgae()},toggleAll:function(t){var o=this,e=t.done;this.todos.forEach(function(t){t.done=e,o.setLocalStorgae()})}},filters:{pluralize:function(t,o){return 1===t?o:o+"s"},capitalize:function(t){return t.charAt(0).toUpperCase()+t.slice(1)}}}},KT9X:function(t,o,e){var i=e("83sx");"string"==typeof i&&(i=[[t.i,i,""]]),i.locals&&(t.exports=i.locals);e("rjj0")("d88a37a6",i,!0)},g0Ed:function(t,o,e){"use strict";var i=function(){var t=this,o=t.$createElement,e=t._self._c||o;return e("li",{staticClass:"todo",class:{completed:t.todo.done,editing:t.editing}},[e("div",{staticClass:"view"},[e("input",{staticClass:"toggle",attrs:{type:"checkbox"},domProps:{checked:t.todo.done},on:{change:function(o){t.toggleTodo(t.todo)}}}),t._v(" "),e("label",{domProps:{textContent:t._s(t.todo.text)},on:{dblclick:function(o){t.editing=!0}}}),t._v(" "),e("button",{staticClass:"destroy",on:{click:function(o){t.deleteTodo(t.todo)}}})]),t._v(" "),e("input",{directives:[{name:"show",rawName:"v-show",value:t.editing,expression:"editing"},{name:"focus",rawName:"v-focus",value:t.editing,expression:"editing"}],staticClass:"edit",domProps:{value:t.todo.text},on:{keyup:[function(o){if(!("button"in o)&&t._k(o.keyCode,"enter",13,o.key))return null;t.doneEdit(o)},function(o){if(!("button"in o)&&t._k(o.keyCode,"esc",27,o.key))return null;t.cancelEdit(o)}],blur:t.doneEdit}})])},n=[],a={render:i,staticRenderFns:n};o.a=a},jfHn:function(t,o,e){"use strict";function i(t){e("KT9X")}Object.defineProperty(o,"__esModule",{value:!0});var n=e("DVEb"),a=e.n(n),d=e("kxv+"),l=e("VU/8"),r=i,s=l(a.a,d.a,!1,r,null,null);o.default=s.exports},"kxv+":function(t,o,e){"use strict";var i=function(){var t=this,o=t.$createElement,e=t._self._c||o;return e("section",{staticClass:"todoapp"},[e("header",{staticClass:"header"},[e("input",{staticClass:"new-todo",attrs:{autocomplete:"off",placeholder:"Todo List"},on:{keyup:function(o){if(!("button"in o)&&t._k(o.keyCode,"enter",13,o.key))return null;t.addTodo(o)}}})]),t._v(" "),e("section",{directives:[{name:"show",rawName:"v-show",value:t.todos.length,expression:"todos.length"}],staticClass:"main"},[e("input",{staticClass:"toggle-all",attrs:{id:"toggle-all",type:"checkbox"},domProps:{checked:t.allChecked},on:{change:function(o){t.toggleAll({done:!t.allChecked})}}}),t._v(" "),e("label",{attrs:{for:"toggle-all"}}),t._v(" "),e("ul",{staticClass:"todo-list"},t._l(t.filteredTodos,function(o,i){return e("todo",{key:i,attrs:{todo:o},on:{toggleTodo:t.toggleTodo,editTodo:t.editTodo,deleteTodo:t.deleteTodo}})}))]),t._v(" "),e("footer",{directives:[{name:"show",rawName:"v-show",value:t.todos.length,expression:"todos.length"}],staticClass:"footer"},[e("span",{staticClass:"todo-count"},[e("strong",[t._v(t._s(t.remaining))]),t._v("\n      "+t._s(t._f("pluralize")(t.remaining,"item"))+" left\n    ")]),t._v(" "),e("ul",{staticClass:"filters"},t._l(t.filters,function(o,i){return e("li",{key:i},[e("a",{class:{selected:t.visibility===i},on:{click:function(o){o.preventDefault(),t.visibility=i}}},[t._v(t._s(t._f("capitalize")(i)))])])}))])])},n=[],a={render:i,staticRenderFns:n};o.a=a},lF9d:function(t,o,e){"use strict";Object.defineProperty(o,"__esModule",{value:!0}),o.default={name:"Todo",props:["todo"],data:function(){return{editing:!1}},directives:{focus:function(t,o,e){var i=o.value,n=e.context;i&&n.$nextTick(function(){t.focus()})}},methods:{deleteTodo:function(t){this.$emit("deleteTodo",t)},editTodo:function(t){var o=t.todo,e=t.value;this.$emit("editTodo",{todo:o,value:e})},toggleTodo:function(t){this.$emit("toggleTodo",t)},doneEdit:function(t){var o=t.target.value.trim(),e=this.todo;o?this.editing&&(this.editTodo({todo:e,value:o}),this.editing=!1):this.deleteTodo({todo:e})},cancelEdit:function(t){t.target.value=this.todo.text,this.editing=!1}}}},xjg9:function(t,o,e){"use strict";Object.defineProperty(o,"__esModule",{value:!0});var i=e("lF9d"),n=e.n(i),a=e("g0Ed"),d=e("VU/8"),l=d(n.a,a.a,!1,null,null,null);o.default=l.exports}});
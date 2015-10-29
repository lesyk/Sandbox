var TodoList = React.createClass({displayName: "TodoList",
  handleTodoCheck: function(id) {
    this.props.onTodoCheck({id: id});
    return;
  },
  render: function() {
    var todoNodes = this.props.data.map(function(todo) {
      return (
        React.createElement(Todo, {id: todo._id, onTodoCheck: this.handleTodoCheck}, 
          todo.text
        )
      );
    }, this);

    var style = {
      width: '100%'
    };

    return (
      React.createElement("div", {className: "row todoList"}, 
        React.createElement("div", {className: "col-md-4 col-md-offset-4"}, 
          React.createElement("div", {className: "progress"}, 
            React.createElement("div", {className: "progress-bar progress-bar-striped active", role: "progressbar", "aria-valuenow": "45", "aria-valuemin": "0", "aria-valuemax": "100", style: style}, 
              React.createElement("span", {className: "sr-only"}, "100% Complete")
            )
          ), 
          React.createElement("div", {className: "list"}, 
            todoNodes
          )
        )
      )
    );
  }
});

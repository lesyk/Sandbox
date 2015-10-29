var TodoBox = React.createClass({displayName: "TodoBox",
  ajaxProgress: function () {
    $('.todoList .progress').removeClass('hidden');
    $('.todoList .list').addClass('hidden');
  },
  ajaxComplete: function() {
    $('.todoList .progress').addClass('hidden');
    $('.todoList .list').removeClass('hidden');
  },
  loadTodosFromServer: function() {
    this.ajaxProgress();

    $.ajax({
      url: this.props.url,
      dataType: 'json',
      success: function(data) {
        this.ajaxComplete();

        this.setState({data: data});
      }.bind(this),
      error: function(xhr, status, err) {
        console.error(this.props.url, status, err.toString());
      }.bind(this),
    });
  },
  handleTodoSubmit: function(todo) {
    var todos = this.state.data;
    var newTodos = todos.concat([todo]);
    this.setState({data: newTodos});
    $.ajax({
      url: this.props.url,
      dataType: 'json',
      type: 'POST',
      data: todo,
      success: function(data) {
        this.setState({data: data});
      }.bind(this),
      error: function(xhr, status, err) {
        console.error(this.props.url, status, err.toString());
      }.bind(this),
    });
  },
  handleTodoCheck: function(id) {
    this.ajaxProgress();

    $.ajax({
      url: this.props.url + '/' + id.id.id,
      dataType: 'json',
      type: 'DELETE',
      success: function(data) {
        this.ajaxComplete();

        this.setState({data: data});
      }.bind(this),
      error: function(xhr, status, err) {
        console.error(this.props.url, status, err.toString());
      }.bind(this)
    });
  },
  getInitialState: function() {
    return {
      data: []
    };
  },
  componentDidMount: function() {
    this.loadTodosFromServer();
  },
  render: function() {
    return (
      React.createElement("div", {className: "todoBox container"}, 
        React.createElement(TodoHeader, {count: this.state.data.length}), 
        React.createElement(TodoList, {data: this.state.data, onTodoCheck: this.handleTodoCheck}), 
        React.createElement(TodoForm, {onTodoSubmit: this.handleTodoSubmit}), 
        React.createElement(TodoFooter, null)
      )
    );
  }
});

React.render(
  React.createElement(TodoBox, {url: "/api/todos"}),
  document.getElementById("content")
)

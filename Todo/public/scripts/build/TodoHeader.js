var TodoHeader = React.createClass({displayName: "TodoHeader",
  render: function() {
    return (
      React.createElement("div", {className: "todoHeader jumbotron text-center row"}, 
        React.createElement("h1", null, "Still to do: ", React.createElement("label", {className: "label label-info"}, this.props.count))
      )
    );
  }
});

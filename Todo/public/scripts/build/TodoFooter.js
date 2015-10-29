var TodoFooter = React.createClass({displayName: "TodoFooter",
  render: function() {
    return (
      React.createElement("div", {className: "todoFooter row text-center"}, 
        React.createElement("div", null, "See my GitHub ", React.createElement("a", {href: "https://github.com/lesyk/Sandbox"}, "repo"), ".")
      )
    );
  }
});

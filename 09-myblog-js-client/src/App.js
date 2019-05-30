import React from 'react';
import Article from './Article';
import './App.css';

class App extends React.Component {
	state = {
		articles:[]
	};
	
	constructor(){
	super();
	fetch('http://localhost:8080/api/articles')
    	.then(resp => resp.json())
    	.then(articles => {
    		console.log(articles);
			this.setState({articles});
    	});
	}
	
	render() {
	    return this.state.articles.map(
		  article => (<Article key={article.id} value={article} />)
	    );
	}

}

export default App;
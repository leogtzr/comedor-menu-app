import React, { Component } from 'react';
import { 
  // Button, 
  // ButtonGroup, 
  Container, 
  // Table 
} from 'reactstrap';
import AppNavbar from './AppNavbar';
// import { Link } from 'react-router-dom';

class MenuView extends Component {
    
    constructor(props) {
        super(props);
        this.state = {menu: {}, isLoading: true};
        // this.remove = this.remove.bind(this);
    }

    componentDidMount() {
        this.setState({isLoading: true});
        const { title } = this.props.match.params;
    
        fetch(title)
            .then(response => response.json())
            .then(data => this.setState({menu: data, isLoading: false}));
    }

    render() {
        const {menu, isLoading} = this.state;
    
        if (isLoading) {
          return <h2>Loading...</h2>
        }
  
    
        return (
          <div>
            <AppNavbar/>
            <Container fluid>
              <h2>{menu.title}</h2>
              <h3>{menu.menu["MONDAY"].breakfast.name}</h3>
              {/* <Table>
                <thead>
                <tr>
                  <th>Name</th>
                </tr>
                </thead>
                <tbody>
                {groupList}
                </tbody>
              </Table> */}
            </Container>
          </div>
        );
      }
    }
    
    export default MenuView;
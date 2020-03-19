import React, { Component } from 'react';
import { 
  // Button, 
  // ButtonGroup, 
  Container, 
  Table 
} from 'reactstrap';
// import { Link } from 'react-router-dom';
import AppNavbar from './AppNavbar';

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

        const {MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY} = menu.menu;
        const days = [MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY];
        const nameDays = ["Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sabado", "Domingo"];
        const todo = days.map((day, key) => {
          return <tr key="monday">
            <td style={{whiteSpace: 'nowrap'}}>{nameDays[key]}</td>
            <td style={{whiteSpace: 'nowrap', fontSize: 10}}>
              <ul>{day.lunchDinnerFoodAlternatives.map(
                  food => 
                    <li>{food.name} ({food.mealType} {food.kcal} kcal)</li>
                )
                }
              </ul>
            </td>
            <td style={{whiteSpace: 'nowrap'}}>{day.breakfast.name} ({day.breakfast.kcal} kcal)</td>
            <td style={{whiteSpace: 'nowrap', fontSize: 10}}>
              <ul>{day.salads.map(salad => <li>{salad}</li>)}</ul>
            </td>
          </tr>
        });

        return (
          <div>
            <AppNavbar/>
            <Container fluid>
              <h2>{menu.title}</h2> 
              
              <Table>
                <thead>
                <tr>
                  <th>Day</th>
                  <th>Food</th>
                  <th>Breakfast</th>
                  <th>Salads</th>
                </tr>
                </thead>
                <tbody>
                {todo}
                </tbody>
              </Table>
            </Container>
          </div>
        );
      }
    }
    
    export default MenuView;
import { useEffect, useState } from 'react';
import { TabView, TabPanel } from 'primereact/tabview';
import { Card } from 'primereact/card';

import 'primereact/resources/themes/lara-light-indigo/theme.css'; //theme
import 'primereact/resources/primereact.min.css'; //core css
import 'primeicons/primeicons.css'; //icons
import 'primeflex/primeflex.css'; // flex
import CardList from './CardList';
import Forms from './forms/index';
import { Button } from 'primereact/button';
import { InputNumber } from 'primereact/inputnumber';
import { ProgressSpinner } from 'primereact/progressspinner';
const API = "http://localhost:8080/kafka/";

interface Prop {
  sensorName: string;
  macAddress: string;
  minValue: number;
  maxValue: number;
  averageValue: number;
  location: string;
  date: Date;
}

function App() {
  const [sensorData, setSensorData] = useState<Prop[]>([]);
  const [qndt, setQndt] = useState(0);
  const [loading, setLoading] = useState(false);
  useEffect(() => {
    const fetchSensorData = async () => {
      try {
        const response = await fetch(API + 'sensor');
        if (response.ok) {
          const data = await response.json();
          setSensorData(data);
          setLoading(false)
        } else {
          console.error('Error:', response.status);
        }
      } catch (error) {
        console.error('Error:', error);
      }
    };

    const interval = setInterval(fetchSensorData, 15000);

    return () => {
      clearInterval(interval);
    };
  }, []);


  const handleGeneraData = async (event: { preventDefault: () => void; }) => {
    event.preventDefault();
    setLoading(true)
    try {
      const response = await fetch(API + 'publish?data=' + qndt, {
        method: 'GET',
        headers: {
          'Content-Type': 'application/json',
        },
      });

      if (response.ok) {
        console.log('Message sent successfully!');
      } else {
        console.error('Error:', response.status);
      }
    } catch (error) {
      console.error('Error:', error);
    }
  };

  return (
    <div >
      <Card title="Projeto de Sistema Distribuido">
        <p className="m-0">
          O projeto demostrar a aplicabilidade do kafka com spring boot no contexto de aplicações distribuidas.
        </p>
      </Card>
      <TabView>
        <TabPanel header="Lista de Sensores">
          {sensorData.length == 0 ?
            <div>
              {loading ?
                <ProgressSpinner style={{ width: '50px', height: '50px' }} strokeWidth="8" fill="var(--surface-ground)" animationDuration=".5s" />
                 :
                <div>
                  <label htmlFor="minValue" className="font-bold flex flex-column mb-2">Quantidade de dados</label>
                  <InputNumber inputId="minValue" value={qndt} onValueChange={(event) => setQndt(event.value != null ? event.value : 0)} />
                  <Button icon="pi pi-check" onClick={handleGeneraData} />
                </div>
              }
            </div> :
              <CardList dataSensor={sensorData} />
            }
        </TabPanel>
        <TabPanel header="Cadastrar novo Sensor">
          <Forms />
        </TabPanel>
      </TabView>
    </div>
  );
}

export default App;

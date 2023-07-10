import {  useState } from 'react';
import { Button } from 'primereact/button';
import { InputText } from "primereact/inputtext";
import { InputNumber } from 'primereact/inputnumber';

interface Prop {
    sensorName: string;
    macAddress: string;
    minValue: number;
    maxValue: number;
    averageValue: number;
    location: string;
    date: Date;
  }

export default function Forms() {
    const [minValue, setMinValue] = useState(0);
    const [maxValue, setMaxValue] = useState(0);
    const [sensorName, setSensorName] = useState("");
    const [location, setLocation] = useState("");
    const API = "http://localhost:8080/kafka/";

    const handleSubmitSensor= async (event: { preventDefault: () => void; }) => {
        event.preventDefault();
    
        try {
          const response = await fetch(API + 'sensor ', {
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
            },
            body: JSON.stringify({
                sensorName: sensorName,
                macAddress: "04:80:D6:BD:2D:CA",
                minValue: minValue,
                maxValue: maxValue,
                averageValue: (minValue+maxValue)/2,
                location: location,
                date: new Date(),
            }),
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
        <div className="card flex justify-content-center">
            <form onSubmit={handleSubmitSensor} className="flex flex-column gap-2">
                <div className="flex-auto">
                    <label htmlFor="sensorName" className="font-bold flex flex-column mb-2">Sensor Nome</label>
                    <InputText id="sensorName" value={sensorName} onChange={(e) => setSensorName(e.target.value)} />

                    <label htmlFor="location" className="font-bold flex flex-column mb-2">Localizaçõa</label>
                    <InputText id="location" value={location} onChange={(e) => setLocation(e.target.value)} />

                    <label htmlFor="minValue" className="font-bold flex flex-column mb-2">Valor minimo</label>                 
                    <InputNumber inputId="minValue" value={minValue} onValueChange={(event) => setMinValue(event.value!= null?event.value: 0 )} minFractionDigits={2} />
                    
                    <label htmlFor="maxValue" className="font-bold flex flex-column mb-2">Valor maximo</label>
                    <InputNumber inputId="maxValue" value={maxValue} onValueChange={(event) => setMaxValue(event.value!= null?event.value: 0 )} minFractionDigits={2} />
                </div>
                <Button label="Enviar" type="submit" icon="pi pi-check" />
            </form>
        </div>
    )
}

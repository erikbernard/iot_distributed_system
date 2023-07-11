import { DataView } from 'primereact/dataview';
import { Tag } from 'primereact/tag';
import Temperature from "../assets/temperature.png";

interface Prop {
    dataSensor: Sensor[]
}
interface Sensor {
    sensorName: string;
    macAddress: string;
    minValue: number;
    maxValue: number;
    averageValue: number;
    location: string;
    date: Date;
}

export default function CardList({ dataSensor }: Prop) {
    const formatDate = (dateString: Date) => {
        const date = new Date(dateString);
        const day = date.getDate();
        const month = date.getMonth() + 1;
        const year = date.getFullYear();
        return `${day < 10 ? '0' + day : day}/${month < 10 ? '0' + month : month}/${year}`;
      };

      const formatTemperature = (temperature: number ) =>{
        return `${temperature.toFixed(2)} °C`
      }
    const itemSensor = (data: Sensor) => {
        return (
            <div className="col-12 CardList">
                <div className="flex flex-row xl:flex-row xl:align-items-start p-4 gap-4">
                    <img className="w-9 sm:w-16rem xl:w-10rem block xl:block mx-auto border-round" src={Temperature} alt={data.sensorName} />
                    <div className="flex flex-column sm:flex-row justify-content-between align-items-center xl:align-items-start flex-1 gap-4">
                        <div className="flex flex-column align-items-center sm:align-items-start gap-3">
                            <div className="text-2xl font-bold text-900">{data.sensorName}</div>
                            <div className="text-1xl font text-900">{data.macAddress}</div>
                            <div className="text-1xl font text-900">{formatDate(data.date)}</div>
                            <div className="text-1xl font text-900">{data.location}</div>
                            <div className="flex align-items-center gap-3">
                                <Tag className="mr-2" icon="pi pi-info-circle" severity="info">{formatTemperature(data.maxValue)}</Tag>
                                <Tag className="mr-2" icon="pi pi-info-circle" severity="info">{formatTemperature(data.minValue)}</Tag>
                                <Tag className="mr-2" icon="pi pi-info-circle" severity="warning">{formatTemperature(data.averageValue)}</Tag>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    };

    return (
        <div className="card">
            <DataView value={dataSensor} itemTemplate={itemSensor} />
        </div>
    )
}


    // return (
    //     <div className="card">
    //         <Tag className="mr-2" icon="pi pi-user" value="Primary"></Tag>
    //         <Tag className="mr-2" icon="pi pi-check" severity="success" value="Success"></Tag>
    //         <Tag className="mr-2" icon="pi pi-info-circle" severity="info" value="Info"></Tag>
    //         <Tag className="mr-2" icon="pi pi-exclamation-triangle" severity="warning" value="Warning"></Tag>
    //         <Tag icon="pi pi-times" severity="danger" value="Danger"></Tag>
    //     </div>
    // );
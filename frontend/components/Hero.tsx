// components/Hero.tsx
import React from "react";
import { Button } from "./ui/button";
import Image from "next/image";

const Hero: React.FC = () => {
  return (
    <section className="relative flex flex-col items-center justify-center text-center p-8 min-h-screen overflow-hidden bg-zinc-50 dark:bg-zinc-950 transition-colors duration-300">
      <div className="absolute inset-0 h-full w-full bg-zinc-50 bg-[linear-gradient(to_right,#f0f0f0_1px,transparent_1px),linear-gradient(to_bottom,#f0f0f0_1px,transparent_1px)] bg-[size:6rem_4rem]"></div>
      <h1 className="text-4xl md:text-5xl lg:text-7xl font-bold mb-4 text-zinc-900 dark:text-zinc-100 z-[1]">
        Intelligent Tutoring System
      </h1>
      <p className="text-lg md:text-xl lg:text-3xl mb-6 text-zinc-800 dark:text-zinc-300 z-[1]">
        A brief description that captures the essence of your message.
      </p>
      <button className="group relative inline-flex h-12 items-center justify-center overflow-hidden rounded-md border border-neutral-200 bg-transparent px-6 font-medium text-neutral-600 transition-all duration-100 [box-shadow:5px_5px_rgb(82_82_82)] hover:translate-x-[3px] hover:translate-y-[3px] hover:[box-shadow:0px_0px_rgb(82_82_82)]">
        Get Started
      </button>
      <div className="relative mt-10 w-full max-w-6xl rounded-xl border-[15px] border-zinc-700 dark:border-zinc-600 overflow-hidden">
          <Image
            src="https://res.cloudinary.com/dvl7demzb/image/upload/v1728139490/e84641ca-dc7d-48a6-9975-ae09310d8671.png"
            alt="Description of the image"
            layout="responsive"
            width={500}
            height={500}
            className="object-cover rounded-3xl" // Match the rounded corners
          />
        </div>
    </section>
  );
};

export default Hero;
